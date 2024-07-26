package com.eight_potato.ui.header

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * 공통 Header
 */
@Composable
fun SingleTextHeader(
    modifier: Modifier = Modifier,
    title: String,
    onClickCloseButton: (() -> Unit)? = null
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(26.dp),
            text = title
        )
        onClickCloseButton?.let {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable { onClickCloseButton() }
                    .padding(16.dp),
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "이전화면으로 이동"
            )
        }
    }
}