package to.currencyconverter.model

class Calculator(
    private val fromCurrency: Currency,
    private val toCurrency: Currency
) {
    var valueToCalculate: Double = 0.0

    var calculatedValue: Double = 0.0
        private set

    private fun count(): Double =
        valueToCalculate * (fromCurrency.convertRatio * fromCurrency.exchangeRate) /
                (toCurrency.convertRatio * toCurrency.exchangeRate)

    fun calculate() {
        calculatedValue = count()
    }
}
