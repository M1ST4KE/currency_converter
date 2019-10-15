package to.currencyconverter.model

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CurrencyRepositoryTest {

    @BeforeEach
    fun setUp() {
        CurrencyRepository.initialize(CurrenciesFactoryMock())
    }

    @Test
    @DisplayName("should return all elements of set")
    fun test1() {
        // given
        val currencyRepository = CurrencyRepository

        // when
        val output = currencyRepository.currencies

        // then
        assert(
            output.isNotEmpty() &&
            output.size == 4 &&
            output.contains(Currency("dolar amerykański", "USD", 3.9504, 1)) &&
            output.contains(Currency("euro", "EUR", 4.3521, 1)) &&
            output.contains(Currency("złoty polski", "PLN", 1.0, 1)) &&
            output.contains(Currency("kuna chorwacka", "HRK", 0.6412, 1))
        )
    }

    @Test
    @DisplayName("should return specific element of set")
    fun test2() {
        // given
        val currencyRepository = CurrencyRepository
        val currencyToSearch = Currency.build { shortcut = "EUR" }

        // when
        val output = currencyRepository.findCurrency(currencyToSearch)

        // then
        assert(
            output.name == "euro" &&
            output.shortcut == "EUR" &&
            output.exchangeRate == 4.3521 &&
            output.convertRatio == 1
        )
    }
}
