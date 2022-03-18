package com.vsee.news.utils.common

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    const val EMPTY = ""
    const val FORMAT_DATETIME_1 = "yyyy-MM-dd'T'HH:mm:ss"
    const val FORMAT_DATETIME_2 = "MMM dd, yyyy hh:mm a"

    fun simpleStringToDate(dateString: String?, format: String? = FORMAT_DATETIME_1): Date {
        return try {
            SimpleDateFormat(format).parse(dateString)
        } catch (e: Exception) {
            Date()
        }
    }

    fun simpleDateToString(date: Date, format: String? = FORMAT_DATETIME_2): String {
        return try {
            SimpleDateFormat(format).format(date)
        } catch (e: Exception) {
            EMPTY
        }
    }

    fun convertUTCToLocalString(
        dateString: String,
        format: String? = FORMAT_DATETIME_1
    ): String {
        return simpleDateToString(convertUTCToLocalDate(dateString, format))
    }

    fun convertUTCToLocalDate(dateString: String?, format: String? = FORMAT_DATETIME_2): Date {
        return try {
            SimpleDateFormat(format).apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }.parse(dateString)
        } catch (e: Exception) {
            Date()
        }
    }

}