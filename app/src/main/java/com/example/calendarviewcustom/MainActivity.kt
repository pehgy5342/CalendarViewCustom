package com.example.calendarviewcustom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashMap


class MainActivity : AppCompatActivity(),
    CalendarView.OnCalendarSelectListener {

    lateinit var pre: ImageButton
    lateinit var next: ImageButton
    lateinit var tv_month: TextView
    lateinit var reset: TextView
    lateinit var clear: TextView
    lateinit var calendarView: CalendarView
    lateinit var map: MutableMap<String, Calendar>
    var minMonth = 2
    var minDay = 9


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pre = findViewById(R.id.btn_previous)
        next = findViewById(R.id.btn_next)
        tv_month = findViewById(R.id.tv_month)
        reset = findViewById(R.id.tv_reset)
        clear = findViewById(R.id.tv_clear)
        calendarView = findViewById(R.id.calendarView_single)

        map = HashMap()
        map["20220211"]


        calendarView.setOnCalendarSelectListener(this)
        tv_month.text = "${calendarView.curMonth}月${calendarView.curDay}日"
        val calendar = Calendar()


        val map: MutableMap<String, Calendar> = java.util.HashMap()
        map[getSchemeCalendar("2022-02-13", "50").toString()] =
            getSchemeCalendar("2022-02-13", "50")
        map[getSchemeCalendar("2022-02-20", "100").toString()] =
            getSchemeCalendar("2022-02-20", "100")
        map[getSchemeCalendar("2022-02-27", "500").toString()] =
            getSchemeCalendar("2022-02-27", "500")

        calendarView.setSchemeDate(map)

        calendarView.scrollToCalendar(calendar.year, calendar.month, calendar.day)

        pre.setOnClickListener {
            calendarView.scrollToPre(true)
//            tv_month.text = "${calendarView.curMonth}月"
        }

        next.setOnClickListener {
            calendarView.scrollToNext(true)
//            tv_month.text = "${calendarView.curMonth}月"
        }

        //清除
        clear.setOnClickListener {
            calendarView.clearSingleSelect()
        }
        //重選
        reset.setOnClickListener {
            reset()
        }
    }


    private fun getSchemeCalendar(date: String, text: String): Calendar {
        val localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val calendar = Calendar()
        calendar.year = localDate.year
        calendar.month = localDate.monthValue
        calendar.day = localDate.dayOfMonth
//        calendar.schemeColor = -0xbf24db //如果单独标记颜色、则会使用这个颜色
        calendar.scheme = text
        calendar.addScheme(Calendar.Scheme())
        calendar.addScheme(-0xff7800, "假")
        calendar.addScheme(-0xff7800, "节")
        return calendar
    }

    private fun reset() {
        val resetCalendar = Calendar()
//        resetCalendar.month = minMonth
//        resetCalendar.day = minDay
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val d=resetCalendar.isCurrentDay

        //回到今日日期
        calendarView.scrollToCalendar(2022, minMonth, minDay)

//        a = resetCalendar
    }

//    override fun onMonthChange(year: Int, month: Int) {
//        tv_month.text = "${month}月"
//    }

    override fun onCalendarOutOfRange(calendar: Calendar?) {
        TODO("Not yet implemented")
    }

    override fun onCalendarSelect(calendar: Calendar?, isClick: Boolean) {
        tv_month.text = "${calendar!!.month}月${calendar!!.day}日"
    }


}