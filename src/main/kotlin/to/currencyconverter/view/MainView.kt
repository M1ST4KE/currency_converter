package to.currencyconverter.view

import java.util.NoSuchElementException
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty
import kotlin.math.pow
import kotlin.math.roundToInt
import to.currencyconverter.model.Calculator
import to.currencyconverter.model.Currency
import to.currencyconverter.model.CurrencyRepository
import tornadofx.View
import tornadofx.action
import tornadofx.button
import tornadofx.label
import tornadofx.textfield
import tornadofx.vbox

class MainView : View() {
    private val repository = CurrencyRepository
    private val currencyFrom = SimpleStringProperty()
    private val currencyTo = SimpleStringProperty()
    private val amount = SimpleDoubleProperty()
    private val output = SimpleStringProperty()
    private val errorOutput = SimpleStringProperty()
    override val root = vbox {
        label("Konwerter walut")
        button("Pokaż wszystkie waluty") {
            action {
                replaceWith<CurrenciesList>()
            }
        }
        label("Przelicz z")
        vbox {
            label("Kod waluty")
            textfield(currencyFrom)
        }
        vbox {
            label("kwota")
            textfield(amount)
        }
        label("na")
        vbox {
            label("Kod waluty")
            textfield(currencyTo)
        }
        button("Przelicz") {
            action {
                countButtonAction()
            }
        }
        label(output)
        label(errorOutput)
    }

    private fun countButtonAction() {
        errorOutput.value = ""
        output.value = ""
        val from: String? = currencyFrom.value
        val to: String? = currencyTo.value
        if (from != null && to != null && from.isNotBlank() && to.isNotBlank()) {
            try {
                val calculator = Calculator(
                    repository.findCurrency(Currency.build {
                        shortcut = from.toUpperCase()
                    }),
                    repository.findCurrency(Currency.build {
                        shortcut = to.toUpperCase()
                    })
                )
                calculator.valueToCalculate = amount.value
                calculator.calculate()
                output.value =
                    "Wartość ${amount.value} ${from.toUpperCase()} to:" +
                            " ${roundToDecimals(calculator.calculatedValue, 4)} ${to.toUpperCase()}"
            } catch (e: NoSuchElementException) {
                output.value = ""
                errorOutput.value = "Nieznany kod waluty: ${e.message}"
            }
        } else {
            output.value = ""
            errorOutput.value = "Błędny kod waluty"
        }
    }

    private fun roundToDecimals(number: Double, numDecimalPlaces: Int): Double {
        val factor = 10.0.pow(numDecimalPlaces.toDouble())
        return (number * factor).roundToInt() / factor
    }
}
