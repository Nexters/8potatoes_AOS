package com.eight_potato.rest.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.rest.model.RestStopUiModel

@Composable
internal fun RestStopDetailHeader(
    modifier: Modifier = Modifier,
    restStop: RestStopUiModel
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
            .background(Colors.White)
            .padding(vertical = 20.dp, horizontal = 40.dp)
    ) {
        Text(
            text = restStop.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "운영시간 ${restStop.operatingTime}  |  ${restStop.state.text}  |  평점 ${restStop.rate}"
        )
    }
}