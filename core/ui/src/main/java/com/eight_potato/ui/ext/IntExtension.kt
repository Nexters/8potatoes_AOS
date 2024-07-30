package com.eight_potato.ui.ext

import android.content.Context
import android.util.DisplayMetrics
import java.text.DecimalFormat
import kotlin.math.roundToInt

/**
 * Int를 금액 단위 String으로 변경
 */
private val MONEY_FORMAT = DecimalFormat("#,###")
fun Int?.toMoneyFormat() = this?.let { MONEY_FORMAT.format(this) } ?: ""

/**
 * Integer extension
 */
fun Int.dpToPx(context: Context): Int {
    val density = context.resources.displayMetrics.density
    return (this * density).roundToInt()
}

fun Int.pxToDp(context: Context): Float {
    val density = context.resources.displayMetrics.densityDpi.toFloat()
    return this / (density / DisplayMetrics.DENSITY_DEFAULT)
}