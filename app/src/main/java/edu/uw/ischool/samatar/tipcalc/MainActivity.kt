package edu.uw.ischool.samatar.tipcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextAmount: EditText = findViewById(R.id.editTextAmount)
        val buttonTip: Button = findViewById(R.id.buttonTip)

        editTextAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val input = s.toString()
                if (input.isNotEmpty() && input != "$") {
                    try {
                        val amount = input.replace("$", "").toDouble()
                        val formattedAmount = DecimalFormat("$#.##").format(amount)
                        editTextAmount.removeTextChangedListener(this)
                        editTextAmount.setText(formattedAmount)
                        editTextAmount.setSelection(formattedAmount.length)
                        editTextAmount.addTextChangedListener(this)
                        buttonTip.isEnabled = true
                    } catch (e: NumberFormatException) {
                        editTextAmount.error = "Invalid input"
                        buttonTip.isEnabled = false
                    }
                } else {
                    buttonTip.isEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        buttonTip.setOnClickListener {
            val amount = editTextAmount.text.toString().substring(1).toDouble()
            val tip = amount * 0.15
            val formattedTip = DecimalFormat("$#.##").format(tip)
            val toastMessage = "Tip: $formattedTip"
            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
        }
    }
}


