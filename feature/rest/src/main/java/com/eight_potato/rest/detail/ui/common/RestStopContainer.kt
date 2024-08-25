package com.eight_potato.rest.detail.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.designsystem.theme.Typo

@Composable
internal fun RestStopContainer(
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier.padding(vertical = 40.dp, horizontal = 20.dp),
        content = content
    )
}

@Composable
internal fun RestStopHeader(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    title: String,
    desc: String? = null
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = icon),
            contentDescription = ""
        )
        Text(
            modifier = Modifier.padding(start = 12.dp),
            text = title,
            style = Typo.HeadSB20
        )
        Spacer(modifier = Modifier.weight(1f))
        if (desc.isNullOrBlank().not()) {
            Text(
                text = desc ?: "",
                style = Typo.BodySB14,
                color = Colors.Blk40
            )
        }
    }
}