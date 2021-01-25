package dev.filipebezerra.android.tipcalculator.calculator

import android.app.Application
import dev.filipebezerra.android.tipcalculator.R
import dev.filipebezerra.android.tipcalculator.domain.RestaurantCalculator
import dev.filipebezerra.android.tipcalculator.domain.TipCalculation
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class CalculatorViewModelTest {
    lateinit var calculatorViewModel: CalculatorViewModel

    @Mock
    lateinit var mockCalculator: RestaurantCalculator

    @Mock
    lateinit var mockApplication: Application

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        stubResource(0.0, "$ 0.00")
        calculatorViewModel = CalculatorViewModel(mockApplication, mockCalculator)
    }

    private fun stubResource(givenAmount: Double, returnStub: String) {
        `when`(mockApplication.getString(R.string.dollar_amount, givenAmount))
            .thenReturn(returnStub)
    }

    @Test
    fun given_correct_amount_and_tip_percentage_when_calculate_tip_then_check_calculation() {
        calculatorViewModel.checkAmountInput = "100.0"
        calculatorViewModel.tipPercentageInput = "10"

        val stub = TipCalculation(
            checkAmount = 100.0,
            tipPercentage = 10,
            tipAmount = 10.0,
            grandTotal = 110.0
        )

        `when`(mockCalculator.calculateTip(checkAmountInput = 100.0, tipPercentageInput = 10))
            .thenReturn(stub)

        stubResource(100.0, "$ 100.00")
        stubResource(10.0, "$ 10.00")
        stubResource(110.0, "$ 110.00")

        calculatorViewModel.calculateTip()
        assertEquals("$ 100.00", calculatorViewModel.billAmountOutput)
        assertEquals("$ 10.00", calculatorViewModel.tipAmountOutput)
        assertEquals("$ 110.00", calculatorViewModel.totalOutput)
    }

    @Test
    fun `given_bad_check_amount_when_calculate_tip_then_check_calculation_wasn't_called`() {
        calculatorViewModel.checkAmountInput = ""
        calculatorViewModel.tipPercentageInput = "10"

        calculatorViewModel.calculateTip()
        verify(mockCalculator, never()).calculateTip(anyDouble(), anyInt())
    }

    @Test
    fun given_bad_tip_percentage_when_calculate_tip_then_check_there_is_no_calculation() {
        calculatorViewModel.checkAmountInput = "100"
        calculatorViewModel.tipPercentageInput = ""

        calculatorViewModel.calculateTip()
        verify(mockCalculator, never()).calculateTip(anyDouble(), anyInt())
    }
}