package com.eight_potato.ui.ext

import java.time.LocalDate

/**
 * YYYY.MM.dd
 */
fun LocalDate.fullFormat(): String {
    return "${year}.${monthValue.padStartWithZeros()}.${dayOfMonth.padStartWithZeros()}"
}