package to.currencyconverter.model

class CurrenciesFactoryMock : CurrenciesFactory {
    override fun generateSet(): HashSet<Currency> =
        hashSetOf(
            Currency("dolar amerykański", "USD", 3.9504, 1),
            Currency("euro", "EUR", 4.3521, 1),
            Currency("złoty polski", "PLN", 1.0, 1),
            Currency("kuna chorwacka", "HRK", 0.6412, 1)
        )
}
