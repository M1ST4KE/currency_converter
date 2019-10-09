package model

data class CurrencyRepository(
    private val currenciesFactory: CurrenciesFactory
) {
    val currencies: HashSet<Currency> = currenciesFactory.generateSet()
}

interface CurrenciesFactory {
    fun generateSet(): HashSet<Currency>
}
