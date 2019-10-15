package to.currencyconverter.view

import to.currencyconverter.model.Currency
import to.currencyconverter.model.CurrencyRepository
import tornadofx.View
import tornadofx.action
import tornadofx.button
import tornadofx.observable
import tornadofx.readonlyColumn
import tornadofx.smartResize
import tornadofx.tableview
import tornadofx.vbox

class CurrenciesList : View("My View") {
    private val repository = CurrencyRepository
    override val root = vbox {
        button("Powrót") {
            action {
                replaceWith<MainView>()
            }
        }
        tableview(repository.currencies.toList().observable()) {
            readonlyColumn("Nazwa", Currency::name)
            readonlyColumn("Skrót", Currency::shortcut)
            readonlyColumn("Przelicznik", Currency::convertRatio)
            readonlyColumn("Kurs średni", Currency::exchangeRate)
            smartResize()
        }
    }
}
