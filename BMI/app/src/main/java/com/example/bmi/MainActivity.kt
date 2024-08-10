package com.example.bmi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val Wedit = findViewById<EditText>(R.id.Wedit)
        val Hedit = findViewById<EditText>(R.id.Hedit)
        val result_btn = findViewById<Button>(R.id.reult_btn)


        result_btn.setOnClickListener {
            val weight = Wedit.text.toString()
            val height = Hedit.text.toString()
            if (validation(weight , height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                val bmi2digit = String.format("%.2f", bmi).toFloat()
                DisplayResult(bmi2digit)
            }
        }
    }
    fun validation(weight : String , height : String) : Boolean{
       return when{
            weight.isNullOrEmpty()->{
                Toast.makeText(this@MainActivity ,"Enter The Weight" , Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this@MainActivity ,"Enter The Height" , Toast.LENGTH_LONG).show()
                return false
            }else->{
                return true
            }
        }
    }

    private fun DisplayResult(bmi : Float){
        val result_txt = findViewById<TextView>(R.id.result_txt)
        val helth_txt = findViewById<TextView>(R.id.helth_txt)
        val av_txt = findViewById<TextView>(R.id.av_txt)

        result_txt.text =bmi.toString()
        av_txt.text = "(Normal Range Is 18.5 - 24.9)"
        var resulttext = ""
        var color = 0

        when{
            bmi<18.5 ->{
                resulttext = "UnderWeight"
                color = R.color.underweight
            }
            bmi in 18.50..24.99->{
                resulttext = "Helthy"
                color = R.color.normal
            }
            bmi in 25.00 .. 29.99->{
                resulttext = "OverWeight"
                color = R.color.OverWeight
            }
            bmi > 29.00->{
                resulttext = "Observ"
                color = R.color.Observ
            }
        }
        helth_txt.setTextColor(ContextCompat.getColor(this ,color))
        helth_txt.text = resulttext
    }
}