package model

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


class CalculatorTest {

    @Test
    @DisplayName("shoud return converted value")
    fun test() {
        // given
        val fromCurrency = Currency("test1", " TS1", 3.0, 1)
        val toCurrency = Currency("test2", " TS2", 2.0, 1)
        val calculator = Calculator(fromCurrency, toCurrency)
        calculator.valueToCalculate = 100.0

        // when
        calculator.calculate()

        // then
        assert(calculator.calculatedValue == 150.0)
    }
}