package com.eight_potato.rest.list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.ui.model.address.AddressUiModel

/**
 * 경로 상의 휴게소를 볼 수 있는 리스트 화면에서
 * 목적지와 출발지를 표시하는 Header
 */
@Composable
internal fun RestListHeader(
    modifier: Modifier = Modifier,
    start: AddressUiModel?,
    end: AddressUiModel?,
    onClickStart: () -> Unit,
    onClickEnd: () -> Unit
) {
    Row(
        modifier = modifier.background(
            color = Colors.White,
            shape = RoundedCornerShape(16.dp)
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .clickable { onClickStart() }
                .padding(16.dp),
            text = start?.name ?: "어디서 출발하세요?",
            textAlign = TextAlign.Center
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "ArrowBack"
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .clickable { onClickEnd() }
                .padding(16.dp),
            text = end?.name ?: "어디까지 가세요?",
            textAlign = TextAlign.Center
        )
    }
}