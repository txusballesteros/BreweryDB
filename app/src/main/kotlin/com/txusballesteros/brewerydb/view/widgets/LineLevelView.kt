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

class LineLevelView: View {
  companion object {
    val DASH_SIZE_IN_DP: Float = 5f
    val MAIN_LINE_WEIGHT_IN_DP: Float = 2f
    val REFERENCE_LINE_WEIGHT_IN_DP: Float = 0.5f
    val MAIN_LINE_COLOR: Int = Color.parseColor("#fff57f17")
    val REFERENCE_LINE_COLOR: Int = Color.parseColor("#ffe5e5e5")
  }
  var minValue: Float = 4.5f
  var maxValue: Float = 5.5f
  var value: Float = 5.5f
  private var drawingArea: RectF = RectF()
  private var valueColor: Int = MAIN_LINE_COLOR
  private var referenceColor: Int = REFERENCE_LINE_COLOR
  private var mainLinePaint: Paint
  private var referenceLinePaint: Paint
  private var dashSize = dp2px(DASH_SIZE_IN_DP)

  constructor(context: Context?) : super(context)
  constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

  init {
    setLayerType(LAYER_TYPE_SOFTWARE, null)
    mainLinePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    mainLinePaint.style = Paint.Style.STROKE
    mainLinePaint.strokeWidth = dp2px(MAIN_LINE_WEIGHT_IN_DP)
    mainLinePaint.strokeCap = Paint.Cap.ROUND
    mainLinePaint.color = valueColor
    referenceLinePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    referenceLinePaint.style = Paint.Style.STROKE
    referenceLinePaint.strokeWidth = dp2px(REFERENCE_LINE_WEIGHT_IN_DP)
    referenceLinePaint.strokeCap = Paint.Cap.ROUND
    referenceLinePaint.color = referenceColor
    referenceLinePaint.pathEffect = DashPathEffect(floatArrayOf(dashSize, dashSize), 0f)
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
    drawReferenceLine(canvas, minValue)
    drawReferenceLine(canvas, maxValue)
    drawMainLine(canvas)
  }

  private fun drawReferenceLine(canvas: Canvas?, value: Float) {
    val levelX = drawingArea.bottom - getLevel(value)
    canvas?.drawLine(drawingArea.left, levelX, drawingArea.right, levelX, referenceLinePaint)
  }

  private fun drawMainLine(canvas: Canvas?) {
    val offsetInX = 0.15f
    val offsetInY = 0.05f
    val levelX = getLevel(this.value)
    val x1_A = drawingArea.centerX() - (drawingArea.centerX() * offsetInX)
    val y1_A = drawingArea.bottom - (drawingArea.height() * offsetInY)
    val x2_A = drawingArea.centerX() - (drawingArea.centerX() * offsetInX)
    val y2_A = drawingArea.bottom - levelX
    val x3_A = drawingArea.centerX()
    val y3_A = drawingArea.bottom - levelX
    val x1_B = drawingArea.centerX() + (drawingArea.centerX() * offsetInX)
    val y1_B = drawingArea.bottom - levelX
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
    val minimumValue = Math.min(minValue, this.value)
    val maximumValue = Math.max(maxValue, this.value)
    val difference = maximumValue - minimumValue
    val referenceValue = value - minimumValue
    val scale = drawingArea.height() / difference
    return referenceValue * scale
  }

  private fun dp2px(value: Float)
      = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics)
}