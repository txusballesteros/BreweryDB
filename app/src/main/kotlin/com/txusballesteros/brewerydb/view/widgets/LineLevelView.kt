/*
 * Copyright Txus Ballesteros 2017 (@txusballesteros)
 *
 * This file is part of Foobar.
 *
 * Foobar is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact: Txus Ballesteros <txus.ballesteros@gmail.com>
 */
package com.txusballesteros.brewerydb.view.widgets

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.txusballesteros.brewerydb.R;

class LineLevelView: View {
  companion object {
    val EMPTY_VALUE: Float = 0f
    val DASH_SIZE_IN_DP: Float = 5f
    val MAIN_LINE_WEIGHT_IN_DP: Float = 2f
    val REFERENCE_LINE_WEIGHT_IN_DP: Float = 0.5f
    val MAIN_LINE_COLOR: Int = Color.parseColor("#fff57f17")
    val REFERENCE_LINE_COLOR: Int = Color.parseColor("#ffe5e5e5")
    val VALUES_WINDOW_FILL_COLOR: Int = Color.parseColor("#ffffecce")
  }
  var minimumReferenceValue: Float = EMPTY_VALUE
  var maximumReferenceValue: Float = EMPTY_VALUE
  var value: Float = EMPTY_VALUE
  var maximumValue: Float = EMPTY_VALUE
  var minimumValue: Float = EMPTY_VALUE
  private var drawingArea: RectF = RectF()
  private var mainLineColor: Int = MAIN_LINE_COLOR
  private var referenceLineColor: Int = REFERENCE_LINE_COLOR
  private var referenceBackgroundColor: Int = VALUES_WINDOW_FILL_COLOR
  private var mainLineWeight = dp2px(MAIN_LINE_WEIGHT_IN_DP)
  private var referenceLineWeight = dp2px(REFERENCE_LINE_WEIGHT_IN_DP)
  private val mainLinePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
  private val referenceLinePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
  private val valuesWindowPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
  private val dashSize = dp2px(DASH_SIZE_IN_DP)
  private var showReferenceBackground = true

  constructor(context: Context?) : super(context)
  constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
    initializeAttributes(attrs)
    initializeView()
  }
  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    initializeAttributes(attrs)
    initializeView()
  }

  private fun initializeAttributes(attrs: AttributeSet?) {
    if (attrs != null) {
      val attributes = context.obtainStyledAttributes(attrs, R.styleable.LineLevelView)
      minimumValue = attributes.getFloat(R.styleable.LineLevelView_minValue, minimumValue)
      maximumValue = attributes.getFloat(R.styleable.LineLevelView_maxValue, maximumValue)
      minimumReferenceValue = attributes.getFloat(R.styleable.LineLevelView_minReference, minimumReferenceValue)
      maximumReferenceValue = attributes.getFloat(R.styleable.LineLevelView_maxReference, maximumReferenceValue)
      mainLineWeight = attributes.getDimension(R.styleable.LineLevelView_mainLineWeight, mainLineWeight)
      mainLineColor = attributes.getColor(R.styleable.LineLevelView_mainLineColor, mainLineColor)
      referenceLineColor = attributes.getColor(R.styleable.LineLevelView_referenceLineColor, referenceLineColor)
      referenceLineWeight = attributes.getDimension(R.styleable.LineLevelView_referenceLineWeight, referenceLineWeight)
      referenceBackgroundColor = attributes.getColor(R.styleable.LineLevelView_referenceBackgroundColor, referenceBackgroundColor)
      showReferenceBackground = attributes
                                  .getBoolean(R.styleable.LineLevelView_showReferenceBackground, showReferenceBackground)
      attributes.recycle()
    }
  }

  fun initializeView() {
    setLayerType(LAYER_TYPE_SOFTWARE, null)
    mainLinePaint.style = Paint.Style.STROKE
    mainLinePaint.strokeWidth = mainLineWeight
    mainLinePaint.strokeCap = Paint.Cap.ROUND
    mainLinePaint.color = mainLineColor
    referenceLinePaint.style = Paint.Style.STROKE
    referenceLinePaint.strokeWidth = referenceLineWeight
    referenceLinePaint.strokeCap = Paint.Cap.ROUND
    referenceLinePaint.color = referenceLineColor
    referenceLinePaint.pathEffect = DashPathEffect(floatArrayOf(dashSize, dashSize), 0f)
    valuesWindowPaint.style = Paint.Style.FILL
    valuesWindowPaint.color = referenceBackgroundColor
    if (isInEditMode) {
      maximumValue = 6f
      minimumValue = 0f
      minimumReferenceValue = 2.5f
      maximumReferenceValue = 5.5f
      value = 5.3f
    }
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    val width = MeasureSpec.getSize(widthMeasureSpec)
    val height = MeasureSpec.getSize(heightMeasureSpec)
    val padding = dp2px(MAIN_LINE_WEIGHT_IN_DP)
    val left = padding
    val top = padding
    val right = width - (padding * 2)
    val bottom = height - (padding * 2)
    drawingArea = RectF(left, top, right, bottom)
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
  }

  override fun onDraw(canvas: Canvas?) {
    drawValuesWindow(canvas)
    drawReferenceLine(canvas, minimumReferenceValue)
    drawReferenceLine(canvas, maximumReferenceValue)
    if (value > minimumValue) {
      drawMainLine(canvas)
    }
  }

  private fun drawValuesWindow(canvas: Canvas?) {
    if (showReferenceBackground && maximumReferenceValue > minimumReferenceValue) {
      val maximumValueY = getLevel(maximumReferenceValue)
      val minimumValueY = getLevel(minimumReferenceValue)
      canvas?.drawRect(RectF(drawingArea.left, maximumValueY, drawingArea.right, minimumValueY), valuesWindowPaint)
    }
  }

  private fun drawReferenceLine(canvas: Canvas?, value: Float) {
    if (value > EMPTY_VALUE) {
      val levelY = getLevel(value)
      canvas?.drawLine(drawingArea.left, levelY, drawingArea.right, levelY, referenceLinePaint)
    }
  }

  private fun drawMainLine(canvas: Canvas?) {
    val offsetInX = 0.15f
    val offsetInY = 0.05f
    val levelY = getLevel(this.value)
    val x1_A = drawingArea.centerX() - (drawingArea.centerX() * offsetInX)
    val y1_A = drawingArea.bottom - (drawingArea.height() * offsetInY)
    val x2_A = drawingArea.centerX() - (drawingArea.centerX() * offsetInX)
    val y2_A = levelY
    val x3_A = drawingArea.centerX()
    val y3_A = levelY
    val x1_B = drawingArea.centerX() + (drawingArea.centerX() * offsetInX)
    val y1_B = levelY
    val x2_B = drawingArea.centerX() + (drawingArea.centerX() * offsetInX)
    val y2_B = drawingArea.bottom - (drawingArea.height() * offsetInY)
    val x3_B = drawingArea.right
    val y3_B = drawingArea.bottom
    val path = Path()
    path.moveTo(drawingArea.left, drawingArea.bottom)
    path.cubicTo(x1_A, y1_A, x2_A, y2_A, x3_A, y3_A)
    path.cubicTo(x1_B, y1_B, x2_B, y2_B, x3_B, y3_B)
    canvas?.drawPath(path, mainLinePaint)
  }

  private fun getLevel(value: Float): Float {
    var minimumValue = Math.min(minimumValue, this.value)
        minimumValue = Math.min(minimumValue, this.minimumReferenceValue)
    var maximumValue = Math.max(maximumValue, this.value)
        maximumValue = Math.max(maximumValue, this.maximumReferenceValue)
    val difference = maximumValue - minimumValue
    val referenceValue = value - minimumValue
    val scale = drawingArea.height() / difference
    return drawingArea.bottom - (referenceValue * scale)
  }

  private fun dp2px(value: Float)
      = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics)
}