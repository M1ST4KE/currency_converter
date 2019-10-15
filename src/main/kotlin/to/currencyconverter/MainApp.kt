package to.currencyconverter

import javafx.scene.text.FontWeight
import javafx.stage.Stage
import to.currencyconverter.model.CurrencyFactoryNbp
import to.currencyconverter.model.CurrencyRepository
import to.currencyconverter.view.MainView
import tornadofx.App
import tornadofx.Stylesheet
import tornadofx.px

class MainApp : App(MainView::class, Styles::class) {
    override fun start(stage: Stage) {
        CurrencyRepository.initialize(CurrencyFactoryNbp())
        with(stage) {
            minWidth = 470.0
            minHeight = 400.0
            super.start(this)
        }
    }
}

class Styles : Stylesheet() {
    init {

        label {
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
        }
    }
}
