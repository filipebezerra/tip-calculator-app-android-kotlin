package dev.filipebezerra.android.tipcalculator.domain

import java.math.BigDecimal

data class TipCalculation(
    val checkAmount: Double = 0.00,
    val tipPercentage: Int = 0,
    val tipAmount: Double = 0.00,
    val grandTotal: Double = 0.00,
)

class RestaurantCalculator {
    fun calculateTip(checkAmountInput: Double, tipPercentageInput: Int): TipCalculation {
        val tipAmount = (checkAmountInput * (tipPercentageInput.toDouble() / 100.00))
            .toBigDecimal()
            .setScale(2, BigDecimal.ROUND_HALF_UP)
            .toDouble()
        val grandTotal = checkAmountInput + tipAmount
        return TipCalculation(
            checkAmount = checkAmountInput,
            tipPercentage = tipPercentageInput,
            tipAmount,
            grandTotal
        )
    }
}