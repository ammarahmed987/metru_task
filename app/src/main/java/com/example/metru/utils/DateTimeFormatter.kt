package com.example.metru.utils

import android.content.Context
import com.example.metru.constant.Constants
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateTimeFormatter @Inject constructor(private val context: Context) {
    fun getTime(date: String): String {
        val format = SimpleDateFormat(Constants.TIME_FORMAT_1)
        val date: Date = format.parse(date)
        val timeformat = SimpleDateFormat(Constants.TIME_FORMAT_2)
        return timeformat.format(date)
    }

    fun getFormattedDate(dateTxt: String):String{
        val format_1 = SimpleDateFormat(Constants.DATE_FORMAT_2)
        val format_2 = SimpleDateFormat(Constants.DATE_FORMAT_3)
        val date: Date = format_1.parse(dateTxt)
        return format_2.format(date)
    }

    fun getTripTimeDifference(dateTxt: String):Long{
        val format = SimpleDateFormat(Constants.DATE_FORMAT_2, Locale.getDefault())
        val tripDate = format.parse(dateTxt)
        val tripTime = tripDate.time
        val currentDateTxt = format.format(Date())
        val currentDate = format.parse(currentDateTxt)
        val currentTime = currentDate.time
        return currentTime - tripTime
    }
}