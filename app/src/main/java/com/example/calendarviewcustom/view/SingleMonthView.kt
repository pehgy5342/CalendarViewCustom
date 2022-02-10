package com.example.calendarviewcustom.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.calendarviewcustom.R
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.MonthView
import kotlin.math.min
import kotlin.math.roundToInt

/**
 * 演示一个变态需求的月视图
 * Created by huanghaibin on 2018/2/9.
 */

class SingleMonthView(context: Context) : MonthView(context) {
    private var mRadius = 0

    /**
     * 自定义魅族标记的文本画笔
     */
    private val mTextPaint = Paint()
    //
    //
    //    /**
    //     * 24节气画笔
    //     */
    //    private Paint mSolarTermTextPaint = new Paint();
    /**
     * 背景圆点
     */
    private val mPointPaint = Paint()

    /**
     * 今天的背景色
     */
    private val mCurrentDayPaint = Paint()

    /**
     * 圆点半径
     */
    private val mPointRadius: Float
    private val mPadding: Int
    private val mCircleRadius: Float

    /**
     * 自定义魅族标记的圆形背景
     */
    private val mSchemeBasicPaint = Paint()

    /**
     * 不可用画笔
     */
    private val mDisablePaint = Paint()
    private val mSchemeBaseLine: Float

    init {
        mTextPaint.textSize = dipToPx(context, 8f).toFloat()
        mTextPaint.color = -0x1
        mTextPaint.isAntiAlias = true
        mTextPaint.isFakeBoldText = true


//        mSolarTermTextPaint.setColor(0xFFeaeaea);
//        mSolarTermTextPaint.setAntiAlias(true);
//        mSolarTermTextPaint.setTextAlign(Paint.Align.CENTER);
//
//        setLayerType(LAYER_TYPE_SOFTWARE, mTextPaint)
//        mTextPaint.setMaskFilter(BlurMaskFilter(30, BlurMaskFilter.Blur.SOLID))
        mSchemeBasicPaint.isAntiAlias = true
        mSchemeBasicPaint.style = Paint.Style.FILL
        mSchemeBasicPaint.textAlign = Paint.Align.CENTER
        mSchemeBasicPaint.isFakeBoldText = true
        mSchemeBasicPaint.color = Color.WHITE
        mCurrentDayPaint.isAntiAlias = true
        mCurrentDayPaint.style = Paint.Style.FILL
        mCurrentDayPaint.color = -0x151516
        mPointPaint.isAntiAlias = true
        mPointPaint.style = Paint.Style.FILL
        mPointPaint.textAlign = Paint.Align.CENTER
        mPointPaint.color = Color.RED
        mCircleRadius = dipToPx(getContext(), 7f).toFloat()
        mPadding = dipToPx(getContext(), 3f)
        mPointRadius = dipToPx(context, 2f).toFloat()
        mDisablePaint.color = -0x606061
        mDisablePaint.isAntiAlias = true
        mDisablePaint.strokeWidth = dipToPx(context, 2f).toFloat()
        mDisablePaint.isFakeBoldText = true
        val metrics = mSchemeBasicPaint.fontMetrics
        mSchemeBaseLine = mCircleRadius - metrics.descent + (metrics.bottom - metrics.top) / 2 + dipToPx(getContext(), 1f)
    }

    override fun onPreviewHook() {
        mCurrentDayPaint.textSize = mCurMonthLunarTextPaint.textSize
        mRadius = min(mItemWidth, mItemHeight) / 11 * 4
    }

    override fun onDrawSelected(canvas: Canvas, calendar: Calendar, x: Int, y: Int, hasScheme: Boolean): Boolean {
        val cx = x + mItemWidth / 2
        val cy = y + (mItemHeight / 2.8).roundToInt()
        canvas.drawCircle(cx.toFloat(), cy.toFloat(), mRadius.toFloat(), mSelectedPaint)
        return true
    }

    //日期下方繪製標點
    override fun onDrawScheme(canvas: Canvas?, calendar: Calendar?, x: Int, y: Int) {
//        val isSelected = isSelected(calendar)
//        if (isSelected) {
//            mPointPaint.color = Color.WHITE
//        } else {
//            mPointPaint.color = Color.GRAY
//        }
//
//        canvas!!.drawCircle(
//            (x + mItemWidth / 2).toFloat(),
//            (y + mItemHeight - 3 * mPadding).toFloat(),
//            mPointRadius,
//            mPointPaint
//        )
    }


    @SuppressLint("ResourceAsColor")
    override fun onDrawText(canvas: Canvas, calendar: Calendar, x: Int, y: Int, hasScheme: Boolean, isSelected: Boolean) {
        val cx = x + mItemWidth / 2
        val cy = y + mItemHeight / 2.8
        val top = y - mItemHeight / 6
        val isInRange = isInRange(calendar)
        val isEnable = !onCalendarIntercept(calendar)
        mCurMonthTextPaint.color = -0xcccccd
        mCurMonthLunarTextPaint.color = -0x303031
        mSchemeTextPaint.color = -0xcccccd
        mSchemeLunarTextPaint.color = -0x303031
        mOtherMonthTextPaint.color = -0x1e1e1f
        mOtherMonthLunarTextPaint.color = -0x1e1e1f


        if (calendar.isCurrentDay && !isSelected){
            canvas.drawCircle(cx.toFloat(),cy.toFloat(),mRadius.toFloat(),mCurrentDayPaint)
        }


        //週六週日的顏色
        if (calendar.isWeekend && calendar.isCurrentMonth) {
            mCurMonthTextPaint.color = Color.RED
            mCurMonthLunarTextPaint.color = -0x303031
            mSchemeTextPaint.color = -0xb76201
            mSchemeLunarTextPaint.color = -0xb76201
            mOtherMonthLunarTextPaint.color = -0xb76201
            mOtherMonthTextPaint.color = -0xb76201
        } else {
            mCurMonthTextPaint.color = -0xcccccd
            mCurMonthLunarTextPaint.color = -0x303031
            mSchemeTextPaint.color = -0xcccccd
            mSchemeLunarTextPaint.color = -0x303031
            mOtherMonthTextPaint.color = -0x1e1e1f
            mOtherMonthLunarTextPaint.color = -0x1e1e1f
        }

        if (isSelected) {
            canvas.drawText(calendar.day.toString(), cx.toFloat(), mTextBaseLine + top,
                mSelectTextPaint)
            canvas.drawText(calendar.scheme
                ?: "-", cx.toFloat(), mTextBaseLine + y + mItemHeight / 4, mCurMonthLunarTextPaint)
        } else {
            canvas.drawText(calendar.day.toString(), cx.toFloat(), mTextBaseLine + top,
                if (calendar.isCurrentMonth && isInRange && isEnable) mCurMonthTextPaint else mOtherMonthTextPaint)
            if (calendar.isCurrentMonth && isInRange && isEnable) {
                canvas.drawText(calendar.scheme
                    ?: "-", cx.toFloat(), mTextBaseLine + y + mItemHeight / 4, mCurMonthLunarTextPaint)
            }
        }
    }

    companion object {
        /**
         * dp转px
         *
         * @param context context
         * @param dpValue dp
         * @return px
         */
        private fun dipToPx(context: Context, dpValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dpValue * scale + 0.5f).toInt()
        }
    }

}