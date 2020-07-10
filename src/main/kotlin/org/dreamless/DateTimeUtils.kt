package org.dreamless

import java.text.SimpleDateFormat
import java.util.*

/**
 * 获取今天、本周、本月、今年 的开始与结束时间，返回类型： String Date
 */
object DateTimeUtils {

    //region 今天开始时间、结束时间，根据需要可删除private

    /**
     * 今天开始时间 Calendar
     */
    private fun getStartOfDay(): Calendar {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DATE)
        calendar.set(year, month, day, 0, 0, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar
    }

    /**
     * 今天开始时间 String
     */
    fun getStartOfDayToString(): String {
        var calendar = getStartOfDay()
        return calendarToString(calendar)
    }

    /**
     * 今天开始时间 Date
     */
    fun getStartOfDayToDate(): Date {
        var calendar = getStartOfDay()
        return calendarToDate(calendar)
    }


    /**
     * 今天结束时间 Calendar
     */
    private fun getEndOfDay(): Calendar {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DATE)
        calendar.set(year, month, day, 23, 59, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        return calendar
    }

    /**
     * 今天开始时间 String
     */
    fun getEndOfDayToString(): String {
        var calendar = getEndOfDay()
        return calendarToString(calendar)
    }

    /**
     * 今天开始时间 Date
     */
    fun getEndOfDayToDate(): Date {
        var calendar = getEndOfDay()
        return calendarToDate(calendar)
    }

    //endregion

    //region 本周开始时间、结束时间，根据需要可删除private

    /**
     * 本周星期一开头 calendar
     */
    private fun getStartOfWeek(): Calendar {
        val calendar = Calendar.getInstance()
        var dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1
        if (dayOfWeek == 0) dayOfWeek = 7
        calendar.add(Calendar.DATE, -dayOfWeek + 1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)// 注意与 HOUR 的区别
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar
    }

    /**
     * 本周星期一开头 String
     */
    fun getStartOfWeekToString(): String {
        var calendar = getStartOfWeek()
        return calendarToString(calendar)
    }

    /**
     * 本周星期一开头 Date
     */
    fun getStartOfWeekToDate(): Date {
        var calendar = getStartOfWeek()
        return calendarToDate(calendar)
    }


    /**
     * 本周星期日结尾 calendar
     */
    private fun getEndOfWeek(): Calendar {
        val calendar = Calendar.getInstance()
        var dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1
        if (dayOfWeek == 0) dayOfWeek = 7
        calendar.add(Calendar.DATE, -dayOfWeek + 7)
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        return calendar
    }

    /**
     * 本周星期日结尾 String
     */
    fun getEndOfWeekToString(): String {
        var calendar = getEndOfWeek()
        return calendarToString(calendar)
    }

    /**
     * 本周星期日结尾 Date
     */
    fun getEndOfWeekToDate(): Date {
        var calendar = getEndOfWeek()
        return calendarToDate(calendar)
    }

    //endregion

    //region 本月开始时间、结束时间，根据需要可删除private

    /**
     * 本月开始第一天开头 Calendar
     */
    private fun getStartOfMonth(): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar
    }

    /**
     * 本月开始第一天开头 String
     */
    fun getStartOfMonthToString(): String {
        var calendar = getStartOfMonth()
        return calendarToString(calendar)
    }

    /**
     * 本月开始第一天开头 Date
     */
    fun getStartOfMonthToDate(): Date {
        var calendar = getStartOfMonth()
        return calendarToDate(calendar)
    }


    /**
     * 本月最后一天结尾 Calendar
     */
    private fun getEndOfMonth(): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        return calendar
    }

    /**
     * 本月最后一天结尾 String
     */
    fun getEndOfMonthToString(): String {
        var calendar = getEndOfMonth()
        return calendarToString(calendar)
    }

    /**
     * 本月最后一天结尾 Date
     */
    fun getEndOfMonthToDate(): Date {
        var calendar = getEndOfMonth()
        return calendarToDate(calendar)
    }

    //endregion

    //region 今年开始时间、结束时间，根据需要可删除private

    /**
     * 今年的第一天开头 Calendar
     */
    private fun getStartOfYear(): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_YEAR, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 1)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar
    }

    /**
     * 今年的第一天开头 String
     */
    fun getStartOfYearToString(): String {
        var calendar = getStartOfYear()
        return calendarToString(calendar)
    }

    /**
     * 今年的第一天开头 Date
     */
    fun getStartOfYearToDate(): Date {
        var calendar = getStartOfYear()
        return calendarToDate(calendar)
    }


    /**
     * 今年的最后一天结尾 Calendar
     */
    private fun getEndOfYear(): Calendar {
        var calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_YEAR,calendar.getActualMaximum(Calendar.DAY_OF_YEAR))
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        return calendar
    }

    /**
     * 今年的最后一天结尾 String
     */
    fun getEndOfYearToString(): String {
        var calendar = getEndOfYear()
        return calendarToString(calendar)
    }

    /**
     * 今年的最后一天结尾 Date
     */
    fun getEndOfYearToDate(): Date {
        var calendar = getEndOfYear()
        return calendarToDate(calendar)
    }

    //endregion

    //region 通用方法，根据需要可删除private

    /**
     * Calendar 转化为 YYYY/MM/dd HH:mm:ss
     */
    private fun calendarToString(calendar: Calendar): String {
        var dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        return dateFormat.format(calendar.time)
    }

    /**
     * Calendar 转化为 Date
     */
    private fun calendarToDate(calendar: Calendar): Date {
        var millis = calendar.timeInMillis
        return Date(millis)
    }

    //endregion
}