package com.eight_potato.rest.detail.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eight_potato.rest.detail.RestStopDetailActivity

@Composable
internal fun RowScope.RestStopDetailTab(
    modifier: Modifier = Modifier,
    tab: RestStopDetailActivity.Companion.RestStopTab,
    isCurrentStep: Boolean,
    onClickTab: (RestStopDetailActivity.Companion.RestStopTab) -> Unit
) {
    Text(
        modifier = modifier
            .clickable { onClickTab(tab) }
            .weight(1f)
            .drawBehind {
                drawLine(
                    strokeWidth = 4f,
                    color = Color(0xFFFF7512),
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height)
                )
            }
            .padding(vertical = 20.dp),
        text = tab.text,
        color = if (isCurrentStep) Color(0xFFFF7512) else Color(0xFF929292),
        fontSize = 16.sp,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center
    )
}