package com.eight_potato.rest.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.designsystem.theme.Typo
import com.eight_potato.rest.detail.RestStopDetailActivity
import com.eight_potato.rest.detail.RestStopDetailActivity.Companion.RestStopTab

@Composable
internal fun RestSTopDetailTab(
    modifier: Modifier = Modifier,
    currentTab: RestStopTab,
    onClickTab: (RestStopTab) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            RestStopTab.values().forEach {
                RestStopDetailTabItem(
                    tab = it,
                    isCurrentStep = it == currentTab,
                    onClickTab = onClickTab
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Colors.Bg50)
        )
    }
}

@Composable
private fun RowScope.RestStopDetailTabItem(
    modifier: Modifier = Modifier,
    tab: RestStopDetailActivity.Companion.RestStopTab,
    isCurrentStep: Boolean,
    onClickTab: (RestStopDetailActivity.Companion.RestStopTab) -> Unit
) {
    Column(
        modifier
            .clickable { onClickTab(tab) }
            .weight(1f)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            text = tab.text,
            color = if (isCurrentStep) Colors.Main100 else Colors.Blk40,
            style = Typo.BodyB16,
            textAlign = TextAlign.Center
        )
        if (isCurrentStep) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(
                        color = Colors.Main100,
                        shape = RoundedCornerShape(100)
                    )
            )
        }
    }
}