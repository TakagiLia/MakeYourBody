package com.example.makeyourbody

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CommonFormatter {

    //日付変換
    fun dateConvert(dateString : String) : Date {

        val dateStringReplace = dateString.replace('/', '-')
        val local = Locale.getDefault()
        val formatter = SimpleDateFormat("yyyy-MM-dd",local)
        return formatter.parse(dateStringReplace)

    }
}