package com.eight_potato.designsystem.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.surface.HyusikSurface
import com.eight_potato.designsystem.theme.Colors

@Composable
fun HyusikButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(16.dp),
    border: BorderStroke? = null,
    alpha: Float = 1f,
    backgroundColor: Color = Color.Unspecified,
    disabledBackgroundColor: Color = Color.Unspecified,
    contentColor: Color = Colors.White,
    disableContentColor: Color = Colors.Gray500,
    contentPadding: PaddingValues = PaddingValues(22.dp),
    content: @Composable RowScope.() -> Unit
) {
    HyusikSurface(
        modifier = modifier.clickable(onClick = onClick),
        shape = shape,
        color = if (enabled) backgroundColor else disabledBackgroundColor,
        contentColor = if (enabled) contentColor else disableContentColor,
        alpha = alpha,
        contentPadding = contentPadding,
        border = border
    ) {
        ProvideTextStyle(value = MaterialTheme.typography.body1) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                content = content
            )
        }
    }
}