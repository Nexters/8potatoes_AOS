package com.eight_potato.designsystem.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.surface.HyusikSurface
import com.eight_potato.designsystem.theme.Colors

/**
 * 기본 Chip Component
 */
@Composable
fun HyusikChip(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    shape: Shape = MaterialTheme.shapes.small,
    paddingValues: PaddingValues = PaddingValues(4.dp),
    backgroundColor: Color = Colors.Gray300,
    border: BorderStroke? = BorderStroke(width = 1.dp, color = backgroundColor),
    contentColor: Color = Colors.White,
    alpha: Float = 1f,
    content: @Composable RowScope.() -> Unit
) {
    HyusikSurface(
        modifier = modifier.then(
            onClick?.let {
                Modifier.clickable(
                    role = Role.Button,
                    onClick = it
                )
            } ?: Modifier
        ),
        color = backgroundColor,
        shape = shape,
        alpha = alpha,
        border = border,
        contentColor = contentColor
    ) {
        Row(
            modifier = Modifier.padding(paddingValues),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}