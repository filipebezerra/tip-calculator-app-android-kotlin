package dev.filipebezerra.android.tipcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import dev.filipebezerra.android.tipcalculator.databinding.TipCalculatorActivityBinding

class TipCalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<TipCalculatorActivityBinding>(this, R.layout.tip_calculator_activity)
            .apply {
                setSupportActionBar(toolbar)
            }
    }
}