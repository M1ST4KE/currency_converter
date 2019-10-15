package to.currencyconverter.model

import java.util.NoSuchElementException

object CurrencyRepository {
    lateinit var currencies: HashSet<Currency>

    fun initialize(currenciesFactory: CurrenciesFactory) {
        currencies = currenciesFactory.generateSet()
    }

    fun findCurrency(currency: Currency): Currency =
        currencies.find { it == currency }
        ?: throw NoSuchElementException(currency.shortcut)
}

interface CurrenciesFactory {
    fun generateSet(): HashSet<Currency>
}
