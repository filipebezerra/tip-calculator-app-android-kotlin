package dev.filipebezerra.android.tipcalculator.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dev.filipebezerra.android.tipcalculator.databinding.CalculatorFragmentBinding

class CalculatorFragment : Fragment() {
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = CalculatorFragmentBinding.inflate(inflater, container, false)
        .apply {
            viewModel = this@CalculatorFragment.viewModel
        }
        .root
}