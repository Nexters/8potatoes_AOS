package com.eight_potato.designsystem.dot

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.theme.Colors

@Composable
fun BaseDot(
    modifier: Modifier = Modifier,
    size: Dp = 3.dp,
    color: Color = Colors.Blk80
) {
    Canvas(
        modifier = modifier.size(size)
    ) {
        drawCircle(
            color = color
        )
    }
}