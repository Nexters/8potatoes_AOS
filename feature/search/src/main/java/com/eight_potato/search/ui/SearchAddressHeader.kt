package com.eight_potato.search.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.theme.HyusikMatjuTheme

/**
 * 주소 검색 Header
 */
@Composable
internal fun SearchAddressHeader(
    modifier: Modifier = Modifier,
    onClickCloseButton: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "위치 설정"
        )
        Icon(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clickable { onClickCloseButton() }
                .padding(16.dp),
            imageVector = Icons.Default.Close,
            contentDescription = "위치 설정 종료"
        )
    }
}

@Preview
@Composable
private fun SearchAddressHeaderPreview() {
    HyusikMatjuTheme {
        SearchAddressHeader {}
    }
}