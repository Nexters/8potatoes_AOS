package com.eight_potato.designsystem.divider

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import com.eight_potato.designsystem.theme.Colors

@Composable
fun VerticalDivider(
    modifier: Modifier = Modifier,
    color: Color = Colors.Gray400,
    alpha: Float = 1f,
    thickness: Float = 3f,
    dash: Float = 0f
) {
    val pathEffect = PathEffect.dashPathEffect(
        floatArrayOf(dash, dash), 0f
    )
    Canvas(modifier = modifier.fillMaxWidth()) {
        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(0f, size.height),
            strokeWidth = thickness,
            alpha = alpha,
            pathEffect = pathEffect
        )
    }
}