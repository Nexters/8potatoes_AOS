package com.eight_potato.ui.header

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.theme.Typo
import com.eight_potato.ui.R

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
            text = title,
            style = Typo.HeadB18
        )
        onClickCloseButton?.let {
            Icon(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 16.dp)
                    .size(32.dp)
                    .align(Alignment.CenterStart)
                    .clickable { onClickCloseButton() },
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = "이전화면으로 이동"
            )
        }
    }
}