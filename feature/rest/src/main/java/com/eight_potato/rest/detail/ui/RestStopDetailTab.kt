package com.eight_potato.rest.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.rest.detail.RestStopDetailActivity

@Composable
internal fun RowScope.RestStopDetailTab(
    modifier: Modifier = Modifier,
    tab: RestStopDetailActivity.Companion.RestStopTab,
    isCurrentStep: Boolean,
    shape: Shape,
    onClickTab: (RestStopDetailActivity.Companion.RestStopTab) -> Unit
) {
    Text(
        modifier = modifier
            .clickable { onClickTab(tab) }
            .weight(1f)
            .background(
                color = if (isCurrentStep) Color(0xFFF8F9FA) else Colors.White
            )
            .background(
                color = if (isCurrentStep) Colors.White else Color(0xFFF8F9FA),
                shape = shape
            )
            .padding(vertical = 20.dp),
        text = tab.text,
        color = if (isCurrentStep) Color(0xFFFF7512) else Color(0xFF929292),
        fontSize = 16.sp,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center
    )
}