package com.vishcustom.ageinmin

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Month
import java.time.Year
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var tvAgeInMinute : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinute = findViewById(R.id.tvAgeInMinute)

        btnDatePicker.setOnClickListener{
            clickDatePicker()

        }
    }


    private fun clickDatePicker(){

        val myCalendar = Calendar.getInstance()
        val year =myCalendar.get(Calendar.YEAR)
        val month =myCalendar.get(Calendar.MONTH)
        val day =myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd =  DatePickerDialog(this,
            {view, selectedyear, selectedmonth, selecteddayOfMonth ->

                Toast.makeText(this,
                    "Year was $selectedyear, month was ${selectedmonth+1}, day of month was $selecteddayOfMonth",
                    Toast.LENGTH_LONG).show()
                val selectedDate="$selecteddayOfMonth/${selectedmonth+1}/$selectedyear"

                tvSelectedDate?.text = selectedDate

                val  sdf =SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate =sdf.parse(selectedDate)

               theDate?.let{
                   val selectedDateInMinutes = theDate.time /60000

                   val currentDate = sdf.parse(sdf.format((System.currentTimeMillis())))
                   currentDate?.let{
                       val currentDateInMinutes = currentDate.time/60000

                       val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                       tvAgeInMinute?.text = differenceInMinutes.toString()
                   }
               }


            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()


    }
}