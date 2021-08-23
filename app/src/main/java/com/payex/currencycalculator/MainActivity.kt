package com.payex.currencycalculator

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.time.LocalDate


class MainActivity : AppCompatActivity() {

    var xRate: Double? = null
    var lastDate: LocalDate? = null

    @RequiresApi(Build.VERSION_CODES.O)
    val mapDate: LocalDate = LocalDate.now()
    val exchangeRates: Map<Pair<String, String>, Double> = mutableMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sync = Sync(call, 60 * 1000);

    }

    val call: Runnable = object : Runnable {
        override fun run() {
            if (xRate == null || lastDate?.isBefore(LocalDate.now())){
                xRate = doRequest(); // kall endepunktet, parse xml og finn nyeset exchange rate.
            }
            handler.postDelayed(this, (60 * 1000).toLong())
        }
    }

    private fun doRequest() : Double {
        val queue = Volley.newRequestQueue(this)
        val url = "https://www.google.com"

// Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                // Parse xml-responsen fra endepunktet. Xml parsing library XmlPullParser? Eventuelt deserialisere om mulig.
                // finne siste element i lista og datoen
                //

            },
            Response.ErrorListener { textView.text = "That didn't work!" })

// Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    val handler = Handler(Looper.getMainLooper())

    inner class Sync(var task: Runnable, time: Long) {
        init {
            handler.removeCallbacks(task)
            handler.postDelayed(task, time)
        }
    }

/*
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
*/


}

