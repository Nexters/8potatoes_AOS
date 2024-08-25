package com.eight_potato.ui.model.menu

import androidx.annotation.DrawableRes
import com.eight_potato.ui.R

enum class MenuType (
    val text: String,
    @DrawableRes val icon: Int?
) {
    NONE("", null),
    RECOMMEND("추천", R.drawable.ic_recommend),
    SNACK("스낵", R.drawable.ic_snack),
    KOREAN("한식", R.drawable.ic_korean),
    WESTERN("양식", R.drawable.ic_western),
    CHINA("중식", R.drawable.ic_china),
    ASIAN("아시안", R.drawable.ic_asian),;

    companion object {
        fun getMenuTypeByText(text: String): MenuType {
            return values().find { it.text == text } ?: NONE
        }
    }
}