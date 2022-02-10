package com.example.calendarviewcustom.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calendarviewcustom.R
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class SingleMonthActivity : AppCompatActivity(), CalendarView.OnMonthChangeListener,
    CalendarView.OnCalendarSelectListener, CalendarView.OnCalendarInterceptListener,
    CalendarView.OnYearChangeListener, CalendarView.OnViewChangeListener {

    lateinit var pre: ImageButton
    lateinit var next: ImageButton
    lateinit var tv_year: TextView
    lateinit var tv_month: TextView
    lateinit var reset: TextView
    lateinit var clear: TextView
    lateinit var back: Button
    lateinit var calendarView: CalendarView
    lateinit var map: MutableMap<String, Calendar>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_month)

        pre = findViewById(R.id.btn_previous)
        next = findViewById(R.id.btn_next)
        tv_month = findViewById(R.id.tv_month)
        tv_year = findViewById(R.id.tv_year)
        reset = findViewById(R.id.tv_reset)
        clear = findViewById(R.id.tv_clear)
        back = findViewById(R.id.btn_back)
        calendarView = findViewById(R.id.calendarView_single)

        initData()

//        calendarView.scrollToCalendar(calendarView.curYear,calendarView.curMonth,calendarView.curDay)
        //標記當下日期
        calendarView.scrollToCurrent()

        //監控已選中日期
        calendarView.setOnCalendarSelectListener(this)
        //監控年份改變
        calendarView.setOnYearChangeListener(this)
        //監控月份改變
        calendarView.setOnMonthChangeListener(this)

        calendarView.setOnViewChangeListener(this)

//        tv_year.text = calendarView.curYear.toString()
        tv_month.text = "${calendarView.curMonth}月 ${calendarView.curYear}年"


        //上一個月
        pre.setOnClickListener {
            calendarView.scrollToPre(true)
        }

        //下一個月
        next.setOnClickListener {
            calendarView.scrollToNext(true)
        }

        //清除目前所選標點
        clear.setOnClickListener {
            calendarView.clearSingleSelect()
        }
        //重選
        reset.setOnClickListener {
            reset()
        }
        //回前頁
        back.setOnClickListener {
            finish()
        }

    }

    private fun initData() {
        val map: MutableMap<String, Calendar> = HashMap()
        map[getSchemeCalendar("2022-02-12", "500").toString()] =
            getSchemeCalendar("2022-02-12", "500")
        map[getSchemeCalendar("2022-02-13", "500").toString()] =
            getSchemeCalendar("2022-02-13", "500")
        map[getSchemeCalendar("2022-02-14", "430").toString()] =
            getSchemeCalendar("2022-02-14", "430")
        map[getSchemeCalendar("2022-02-15", "430").toString()] =
            getSchemeCalendar("2022-02-15", "430")
        map[getSchemeCalendar("2022-02-16", "430").toString()] =
            getSchemeCalendar("2022-02-16", "430")
        map[getSchemeCalendar("2022-02-17", "430").toString()] =
            getSchemeCalendar("2022-02-17", "430")
        map[getSchemeCalendar("2022-02-18", "430").toString()] =
            getSchemeCalendar("2022-02-18", "430")
        map[getSchemeCalendar("2022-02-19", "500").toString()] =
            getSchemeCalendar("2022-02-19", "500")
        map[getSchemeCalendar("2022-02-20", "500").toString()] =
            getSchemeCalendar("2022-02-20", "500")
        map[getSchemeCalendar("2022-02-21", "430").toString()] =
            getSchemeCalendar("2022-02-21", "430")
        map[getSchemeCalendar("2022-02-22", "430").toString()] =
            getSchemeCalendar("2022-02-22", "430")
        map[getSchemeCalendar("2022-02-23", "430").toString()] =
            getSchemeCalendar("2022-02-23", "430")
        map[getSchemeCalendar("2022-02-24", "430").toString()] =
            getSchemeCalendar("2022-02-24", "430")
        map[getSchemeCalendar("2022-02-25", "430").toString()] =
            getSchemeCalendar("2022-02-25", "430")
        map[getSchemeCalendar("2022-02-26", "500").toString()] =
            getSchemeCalendar("2022-02-26", "500")
        map[getSchemeCalendar("2022-02-27", "500").toString()] =
            getSchemeCalendar("2022-02-27", "500")
        map[getSchemeCalendar("2022-02-28", "430").toString()] =
            getSchemeCalendar("2022-02-28", "430")
        map[getSchemeCalendar("2022-03-04", "60").toString()] =
            getSchemeCalendar("2022-03-04", "60")
        map[getSchemeCalendar("2022-03-08", "170").toString()] =
            getSchemeCalendar("2022-03-08", "170")
        map[getSchemeCalendar("2022-03-26", "590").toString()] =
            getSchemeCalendar("2022-03-26", "590")
        map[getSchemeCalendar("2022-03-30", "1100").toString()] =
            getSchemeCalendar("2022-03-30", "1100")

        calendarView.setSchemeDate(map)
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

    //回到今日日期
    private fun reset() {
        val y = calendarView.curYear
        val m = calendarView.curMonth
        val d = calendarView.curDay
//        tv_year.text = y.toString()
        calendarView.scrollToCalendar(y, m, d)
    }

    //月曆切換時月份跟著切換
    override fun onMonthChange(year: Int, month: Int) {
        val c = calendarView.selectedCalendar

        tv_month.text = "${c.month}月 ${c.year}年"
        var day = c.day
        if (day == 0) {
            day = calendarView.curDay
        }
        calendarView.scrollToCalendar(year, month, day)
    }

    override fun onYearChange(year: Int) {
//        tv_year.text = year.toString()
    }

    //點選日期時，所要做的事
    override fun onCalendarSelect(calendar: Calendar, isClick: Boolean) {
        tv_month.text = "${calendar.month}月 ${calendar.year}年"
        var scheme = calendar.scheme
        if (scheme.isNullOrEmpty()) {
            scheme = "0"
        }
        if (isClick) {
            Toast.makeText(this, "已選擇 ${calendar.year}年${calendar.month}月${calendar.day}日\n" + "金額：${scheme} 元", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCalendarOutOfRange(calendar: Calendar?) {
    }

    override fun onCalendarIntercept(calendar: Calendar): Boolean {
       return !get(calendar)
    }

    override fun onCalendarInterceptClick(calendar: Calendar, isClick: Boolean) {
        Toast.makeText(this, calendar.toString() + "拦截不可点击", Toast.LENGTH_SHORT).show()
    }

    override fun onViewChange(isMonthView: Boolean) {
    }

    private fun get(calendar: Calendar): Boolean {
        return map[calendar.toString()] != null
    }


}