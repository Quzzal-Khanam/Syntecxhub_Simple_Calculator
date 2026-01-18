package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNum1 = findViewById<EditText>(R.id.etNum1)
        val etNum2 = findViewById<EditText>(R.id.etNum2)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        // Set listeners for operation buttons
        findViewById<Button>(R.id.btnAdd).setOnClickListener { executeOperation(etNum1, etNum2, tvResult, "+") }
        findViewById<Button>(R.id.btnSub).setOnClickListener { executeOperation(etNum1, etNum2, tvResult, "-") }
        findViewById<Button>(R.id.btnMul).setOnClickListener { executeOperation(etNum1, etNum2, tvResult, "*") }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { executeOperation(etNum1, etNum2, tvResult, "/") }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            etNum1.text.clear()
            etNum2.text.clear()
            tvResult.text = "0"
        }
    }

    private fun executeOperation(et1: EditText, et2: EditText, resultView: TextView, op: String) {
        val s1 = et1.text.toString()
        val s2 = et2.text.toString()

        // Requirement: Small input validation [cite: 33]
        if (s1.isEmpty() || s2.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show()
            return
        }

        val num1 = s1.toDouble()
        val num2 = s2.toDouble()

        // Requirement: Separate logic function (clean functions)
        resultView.text = calculate(num1, num2, op)
    }

    // Clean function for calculation logic
    private fun calculate(n1: Double, n2: Double, op: String): String {
        return when (op) {
            "+" -> (n1 + n2).toString()
            "-" -> (n1 - n2).toString()
            "*" -> (n1 * n2).toString()
            "/" -> {
                // Requirement: Handle divide-by-zero [cite: 33]
                if (n2 == 0.0) "Error: Div by 0" else (n1 / n2).toString()
            }
            else -> "Error"
        }
    }
}