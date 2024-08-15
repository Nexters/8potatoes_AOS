package com.eight_potato.rest.detail.ui.info

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.surface.HyusikSurface
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.designsystem.theme.HyusikMatjuTheme
import com.eight_potato.designsystem.theme.Typo

/**
 * 휴게소 기타 정보 Component
 */
@Composable
internal fun RestStopExtraInfo(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    text: String,
    trailingIcon: (@Composable () -> Unit)? = null
) {
    HyusikSurface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = Colors.Main10,
        contentPadding = PaddingValues(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = icon),
                contentDescription = "",
                tint = Colors.Main100
            )
            Text(
                modifier = Modifier.weight(1f),
                text = text,
                style = Typo.BodySB16,
                color = Colors.Blk100
            )
            trailingIcon?.invoke()
        }
    }
}

@Preview
@Composable
private fun RestStopExtraInfoPreview() {
    HyusikMatjuTheme {
        RestStopExtraInfo(
            icon = com.eight_potato.designsystem.R.drawable.ic_end,
            text = "충남 천안시 동남구 쉼1길 42"
        )
    }
}