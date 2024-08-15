package com.eight_potato.ui.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

object AnnotatedTextBuilder {
    fun generateTextWithStatus(
        text: String,
        highlightColor: Color,
        highlightedText: String
    ): AnnotatedString {
        val index = text.indexOf(highlightedText)
        return buildAnnotatedString {
            append(text.substring(0, index))
            withStyle(SpanStyle(highlightColor)) {
                append(text.substring(index, index + highlightedText.length))
            }
            append(text.substring(index + highlightedText.length))
        }
    }
}