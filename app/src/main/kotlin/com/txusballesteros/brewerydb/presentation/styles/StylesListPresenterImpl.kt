package com.txusballesteros.brewerydb.presentation.styles

import com.txusballesteros.brewerydb.domain.model.Style
import com.txusballesteros.brewerydb.domain.usecase.UseCase
import com.txusballesteros.brewerydb.domain.usecase.styles.GetStylesUseCase
import com.txusballesteros.brewerydb.presentation.AbsPresenter
import com.txusballesteros.brewerydb.domain.model.map
import javax.inject.Inject

class StylesListPresenterImpl @Inject constructor(private val getStylesUseCase: GetStylesUseCase)
                                            : AbsPresenter<StylesListPresenter.View>(), StylesListPresenter {

  override fun onRequestStyles() {
    getStylesUseCase.execute(callback = object : UseCase.UseCaseCallback<List<Style>> {
      override fun onResult(styles: List<Style>) {
        val result = styles.map { style -> style.map(style) }
        getView()?.renderStyles(result)
      }

      override fun onError() {
        getView()?.renderError()
      }
    })
  }
}