package com.payex.currencycalculator

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    val mapDate : LocalDate = LocalDate.now()
    val exchangeRates : Map<Pair<String, String>, Double> = mutableMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun onRegisterBtnClick(view: View) {
        val textFirstName: TextView = findViewById(R.id.textViewFirstName)
        val textLastName: TextView = findViewById(R.id.textViewLastName)
        val textEmail: TextView = findViewById(R.id.textViewEmail)

        val edtTxtFirstName: EditText = findViewById(R.id.editTextViewFirstName)
        val edtTxtLastName: EditText= findViewById(R.id.editTextViewLastName)
        val edtTxtEmail: EditText= findViewById(R.id.editTextEmail)

        textFirstName.text = "First name: ${edtTxtFirstName.text}"
        textLastName.text = "Last name: ${edtTxtLastName.text}"
        textEmail.text = "Email: ${edtTxtEmail.text}"



    }
}

