package com.example.calendarviewcustom.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.calendarviewcustom.R


class MainActivity : AppCompatActivity() {

    lateinit var single: Button
    lateinit var range: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        single = findViewById(R.id.btn_single)
        range = findViewById(R.id.btn_range)


        single.setOnClickListener {
            val intent = Intent(this, SingleMonthActivity::class.java)
            startActivity(intent)
        }

        range.setOnClickListener {
            val intent = Intent(this, RangeMonthActivity::class.java)
            startActivity(intent)
        }

    }

}