package com.example.caculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import com.example.caculatorapp.databinding.ActivityMainBinding
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isNewOp = true
    private var oldNumber = ""
    private var newNumber = ""
    private var operator = ""
    private var result = 0.0
    private var input = ""
    private val ADD = "+"
    private val SUB = "-"
    private val MUL = "*"
    private val DIV = "/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonClear.setOnClickListener {
            binding.edittextInput.setText(EMPTY)
        }

    }

    fun clickNumber(view: View){
        if (isNewOp){
            binding.edittextInput.setText(EMPTY)
        }
        isNewOp = false
        if (view is MaterialTextView) {
            displayToInput(view.text.toString())
        }
    }

    fun nagativeIntegerEvent(view: View){
        input = binding.edittextInput.text.toString()
        if (view is MaterialTextView){
            if(view == binding.buttonNegativeinteger){
                input = "-$input"
            }
            binding.edittextInput.setText(input)
        }
    }

    fun operatorEvent(view: View) {
        isNewOp = true
        oldNumber = binding.edittextInput.text.toString()
        if (view is MaterialTextView) {
            binding.apply {
                operator = when (view) {
                    buttonAddition -> ADD
                    buttonMultiply -> MUL
                    buttonSubtraction -> SUB
                    buttonDivision -> DIV
                    else -> operator
                }
            }
        }
    }

    private fun displayToInput(displayInput: String) {
        binding.edittextInput.text.append(displayInput)
    }

    fun showResult(view: View){
        newNumber = binding.edittextInput.text.toString()
        result = when(operator) {
            ADD -> oldNumber.toDouble() + newNumber.toDouble()
            SUB -> oldNumber.toDouble() - newNumber.toDouble()
            MUL -> oldNumber.toDouble() * newNumber.toDouble()
            DIV -> oldNumber.toDouble() / newNumber.toDouble()
            else -> result
        }
        binding.edittextInput.setText(result.toString())
    }

    fun percentEvent(view: View){
        var percent: Double = binding.edittextInput.text.toString().toDouble()/100
        binding.edittextInput.setText(percent.toString())
        isNewOp = true
    }

    companion object {
        const val EMPTY = ""
    }

}
