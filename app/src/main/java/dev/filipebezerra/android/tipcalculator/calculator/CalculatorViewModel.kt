package dev.filipebezerra.android.tipcalculator.calculator

import android.app.Application
import dev.filipebezerra.android.tipcalculator.R
import dev.filipebezerra.android.tipcalculator.domain.RestaurantCalculator
import dev.filipebezerra.android.tipcalculator.domain.TipCalculation
import dev.filipebezerra.android.tipcalculator.util.binding.ObservableViewModel

class CalculatorViewModel @JvmOverloads constructor(
    application: Application,
    private val calculator: RestaurantCalculator = RestaurantCalculator()
) : ObservableViewModel(application) {
    var checkAmountInput = "0.00"
    var tipPercentageInput = "0"

    var tipCalculation = TipCalculation()
    var billAmountOutput = "$0.00"
    var tipAmountOutput = "$0.00"
    var totalOutput = "$0.00"

    init {
        updateOutputs()
    }

    private fun updateOutputs() {
        billAmountOutput = getApplication<Application>().getString(
            R.string.dollar_amount,
            tipCalculation.checkAmount
        )
        tipAmountOutput =
            getApplication<Application>().getString(
                R.string.dollar_amount,
                tipCalculation.tipAmount
            )
        totalOutput =
            getApplication<Application>().getString(
                R.string.dollar_amount,
                tipCalculation.grandTotal
            )
    }

    fun calculateTip() {
        val checkAmount = checkAmountInput.toDoubleOrNull()
        val tipPercentage = tipPercentageInput.toIntOrNull()
        if (checkAmount != null && tipPercentage != null) {
            tipCalculation = calculator.calculateTip(checkAmount, tipPercentage)
            updateOutputs()
            clearFields()
        }
    }

    private fun clearFields() {
        checkAmountInput = "0.00"
        tipPercentageInput = "0"
        notifyChange()
    }
}