package com.shine56.blue.util

import java.util.regex.Matcher
import java.util.regex.Pattern

class JsonUtil(private val text: String) {
    /**
     * 根据name获取内容
     */
    fun getContentByName(name: String): Matcher{
        return regularText(name)
    }
    private fun regularText(sign: String): Matcher{
        val regex = "$sign\".*\""
        val pattern = Pattern.compile(regex)
        return pattern.matcher(text)
    }
}