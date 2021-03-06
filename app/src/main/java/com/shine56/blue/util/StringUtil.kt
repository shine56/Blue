package com.shine56.blue.util

import java.util.regex.Matcher
import java.util.regex.Pattern

object StringUtil {

    fun regularText(regex: String, text: String): Matcher {
        val pattern = Pattern.compile(regex)
        return pattern.matcher(text)
    }

    /**
     * Unicode编码转中文
     */
    fun decodeUnicode(theString: String): String {
        var aChar: Char
        val len = theString.length
        val outBuffer = StringBuffer(len)
        var x = 0
        while (x < len) {
            aChar = theString[x++]
            if (aChar == '\\') {
                aChar = theString[x++]
                if (aChar == 'u') {
                    // Read the xxxx
                    var value = 0
                    for (i in 0..3) {
                        aChar = theString[x++]
                        value = when (aChar) {
                            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> (value shl 4) + aChar.toInt() - '0'.toInt()
                            'a', 'b', 'c', 'd', 'e', 'f' -> (value shl 4) + 10 + aChar.toInt() - 'a'.toInt()
                            'A', 'B', 'C', 'D', 'E', 'F' -> (value shl 4) + 10 + aChar.toInt() - 'A'.toInt()
                            else -> throw IllegalArgumentException(
                                "Malformed   \\uxxxx   encoding."
                            )
                        }
                    }
                    outBuffer.append(value.toChar())
                } else {
                    if (aChar == 't') aChar = '\t' else if (aChar == 'r') aChar =
                        '\r' else if (aChar == 'n') aChar = '\n'// else if (aChar == 'f') aChar = '\f'
                    outBuffer.append(aChar)
                }
            } else outBuffer.append(aChar)
        }

        return outBuffer.toString()
//        val regex = "\"Contents\":\".*\""
//        val pattern = Pattern.compile(regex)
//
//        val match = pattern.matcher(outBuffer.toString())
//
//        return if (match.find()) {
//            var str = match.group(0)
//            str = str.replace("\"Contents\":\"", "")
//            str = str.replace("\"", "")
//            str = str.replace("<p>", "\n")
//            str = str.replace("</p>", "")
//            str
//        } else {
//            ""
//        }
    }

    /**
     * 去掉某字符
     */
    fun deleteString(str: String, text: String): String{
        return text.replace(str, "")
    }

    /**
     * 替换某字符
     */
    fun replaceString(oldStr: String, newStr: String, text: String): String{
        return text.replace(oldStr,newStr)
    }
}