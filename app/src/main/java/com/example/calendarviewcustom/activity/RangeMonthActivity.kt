package com.example.calendarviewcustom.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.calendarviewcustom.R
import com.haibin.calendarview.CalendarView

class RangeMonthActivity : AppCompatActivity() {

    lateinit var pre: ImageButton
    lateinit var next: ImageButton
    lateinit var tv_year: TextView
    lateinit var tv_month: TextView
    lateinit var reset: TextView
    lateinit var clear: TextView
    lateinit var back: Button
    lateinit var calendarView: CalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_range_month)


        pre = findViewById(R.id.btn_previous)
        next = findViewById(R.id.btn_next)
        tv_month = findViewById(R.id.tv_month)
        tv_year = findViewById(R.id.tv_year)
//        reset = findViewById(R.id.tv_reset)
//        clear = findViewById(R.id.tv_clear)
        back = findViewById(R.id.btn_back)
        calendarView = findViewById(R.id.calendarView_range)


        back.setOnClickListener {
            finish()
        }

    }


}