package com.ibnufauzn.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        funForProcessing()
    }

    private var gender: String? = null

    private fun funForProcessing() {
        // Memproses radio group
        radioGroupGender.setOnCheckedChangeListener { group, _ ->
            val radioGender = findViewById<RadioButton>(group.checkedRadioButtonId)
            gender = radioGender.text.toString()
        }

        gender = when(gender) {
            "Laki - laki" -> "laki - laki"
            else -> "Perempuan"
        }

        // Tombol ditekan
        btnCalculate.setOnClickListener {
            when ( validationInput() ){
                true -> {
                    val valueName: String = valueName.text.toString()
                    val valueTb: Int = valueTb.text.toString().toInt()
                    val valueBb: Int = valueBb.text.toString().toInt()
                    resultBmi.text = getResult(valueName, valueBb, valueTb)
                }
                else -> Toast.makeText(applicationContext, "Harap masukkan input dengan lengkap", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validationInput(): Boolean {
        return !(valueName.text.isNullOrEmpty() || valueTb.text.isNullOrBlank() || valueBb.text.isNullOrEmpty())
    }

    private fun getResult(name: String, valBb: Int, valTb: Int): String {
        val result: Double = valBb / ((valTb * 0.01) * (valTb * 0.01))
        return when {
            result < 18.5 -> {
                """ Hai $name, 
                    | Anda seorang $gender. 
                    | berdasarkan nilai BMI, 
                    | Anda mengalami kekurangan berat badan.
                    | Silahkan konsumsi makanan bernutrisi.
                """.trimMargin()
            }
            result in 18.5..24.5 -> {
                """ Hai $name, 
                    | Anda seorang $gender. 
                    | berdasarkan nilai BMI,
                    | Anda memiliki berat badan yang ideal.
                    | Tetap jaga pola makan dan gaya hidup Anda.
                """.trimMargin()
            }
            result in 24.5..29.9 -> {
                """ Hai $name, 
                    | Anda seorang $gender. 
                    | berdasarkan nilai BMI,
                    | Anda memiliki berat badan yang berlebih.
                    | Atur pola makan dan gaya hidup Anda agar tetap sehat.
                """.trimMargin()
            }
            else -> {
                """ Hai $name, 
                    | Anda seorang $gender. 
                    | berdasarkan nilai BMI,
                    | Anda mengalami obesitas.
                    | Atur pola makan dan gaya hidup Anda agar tetap sehat.
                """.trimMargin()
            }
        }
    }
}