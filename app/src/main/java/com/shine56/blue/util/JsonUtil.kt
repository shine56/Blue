package com.shine56.blue.util

import java.util.regex.Matcher
import java.util.regex.Pattern

class JsonUtil(private val text: String) {
    /**
     * 根据name获取内容
     */
    fun getContentByName(name: String): Matcher{
        return regularText(name, text)
    }
    private fun regularText(sign: String, text: String): Matcher{
        val regex = "$sign\".*\""
        val pattern = Pattern.compile(regex)
        return pattern.matcher(text)
    }
    fun decodeUnicode(dataStr: String): String {
        var start = 0
        var end = 0
        val buffer = StringBuffer()
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2)
            var charStr = ""
            charStr = if (end == -1) {
                dataStr.substring(start + 2, dataStr.length)
            } else {
                dataStr.substring(start + 2, end)
            }
            val letter = charStr.toInt(16).toChar() // 16进制parse整形字符串。
            buffer.append(letter.toString())
            start = end
        }
        return buffer.toString()
    }
}