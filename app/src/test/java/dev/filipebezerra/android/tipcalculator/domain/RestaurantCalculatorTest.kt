package dev.filipebezerra.android.tipcalculator.domain

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RestaurantCalculatorTest {

    lateinit var calculator: RestaurantCalculator

    @Before
    fun setup() {
        calculator = RestaurantCalculator()
    }

    @Test
    fun given_tip_when_calculate_tip_then_check_total() {
        val baseTipCalculation = TipCalculation(checkAmount = 10.00)
        val tipsToTest = listOf(
            baseTipCalculation.copy(tipPercentage = 25, tipAmount = 2.5, grandTotal = 12.50),
            baseTipCalculation.copy(tipPercentage = 15, tipAmount = 1.5, grandTotal = 11.50),
            baseTipCalculation.copy(tipPercentage = 18, tipAmount = 1.8, grandTotal = 11.80),
        )

        tipsToTest.forEach {
            assertEquals(it,
                calculator.calculateTip(
                    it.checkAmount,
                    it.tipPercentage,
                ))
        }
    }
}