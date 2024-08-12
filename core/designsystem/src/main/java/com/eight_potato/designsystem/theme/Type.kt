package com.eight_potato.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.eight_potato.designsystem.R

val suitFontFamily = FontFamily(
    Font(R.font.suit_bold, FontWeight.Bold),
    Font(R.font.suit_extra_bold, FontWeight.ExtraBold),
    Font(R.font.suit_extra_light, FontWeight.ExtraLight),
    Font(R.font.suit_light, FontWeight.Light),
    Font(R.font.suit_medium, FontWeight.Medium),
    Font(R.font.suit_regular, FontWeight.Normal),
    Font(R.font.suit_semibold, FontWeight.SemiBold),
    Font(R.font.suit_thin, FontWeight.Thin),
    Font(R.font.suit_heavy, FontWeight.Black)
)

object Typo {
    val HeadB24 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )
    val HeadB20 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
    val HeadSB20 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    )
    val HeadB18 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
    val ButtonB16 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )
    val ButtonB14 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    )
    val CaptionSB12 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp
    )
    val BodySB18 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    )
    val BodyB16 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )
    val BodySB16 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    )
    val BodyM16 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    )
    val BodyB14 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    )
    val BodySB14 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    )
    val BodyM14 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
    val SmallB12 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    )
    val SmallM12 = TextStyle(
        fontFamily = suitFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    )
}

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)