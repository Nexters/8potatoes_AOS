package com.eight_potato.rest.list.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eight_potato.designsystem.chip.HyusikChip
import com.eight_potato.designsystem.divider.HorizontalDivider
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.designsystem.theme.HyusikMatjuTheme
import com.eight_potato.designsystem.theme.Typo
import com.eight_potato.rest.R
import com.eight_potato.rest.model.RestStopUiModel
import com.eight_potato.rest.model.TEST_REST_STOP
import com.eight_potato.ui.ext.dpToPx
import com.eight_potato.ui.ext.toMoneyFormat

/**
 * 휴게소 리스트 아이템
 */
@Composable
internal fun RestStopItem(
    context: Context = LocalContext.current,
    modifier: Modifier = Modifier,
    restStop: RestStopUiModel,
    isLastItem: Boolean,
    onClickRestStop: (RestStopUiModel) -> Unit
) {
    Column(
        modifier = modifier
            .clickable { onClickRestStop(restStop) }
            .fillMaxWidth()
            .background(
                color = Color(0xFFFFF1E7),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 20.dp)
            .padding(top = 32.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = restStop.name,
                    style = Typo.HeadB18
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = restStop.direction,
                        style = Typo.SmallM12,
                        color = Colors.Blk60
                    )
                    Spacer(modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(width = 1.dp, height = 12.dp)
                        .background(Colors.Blk40))
                    Text(
                        text = "네이버 평점",
                        style = Typo.SmallB12,
                        color = Colors.Sub2100
                    )
                    Icon(
                        modifier = Modifier
                            .size(12.dp)
                            .padding(horizontal = 2.dp),
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = "",
                        tint = Colors.Sub2100
                    )
                    Text(
                        text = restStop.rate.toString(),
                        style = Typo.SmallB12,
                        color = Colors.Sub2100
                    )
                }
            }
            HyusikChip(
                shape = RoundedCornerShape(8.dp),
                paddingValues = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                backgroundColor = Colors.Blk10
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_folk),
                    contentDescription = "",
                    tint = Colors.Blk60
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = restStop.state.text,
                    style = Typo.SmallB12,
                    color = Colors.Blk60
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Colors.White,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 20.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            RestStopInfo(
                title = "휘발유",
                desc = "${restStop.gasolinePrice.toMoneyFormat()}원"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RestStopInfo(
                title = "경유",
                desc = "${restStop.dieselPrice.toMoneyFormat()}원"
            )
            Spacer(modifier = Modifier
                .padding(horizontal = 16.dp)
                .size(width = 1.dp, height = 12.dp)
                .background(Colors.Blk40))
            RestStopInfo(title = "메뉴", desc = "${restStop.menuCount}가지")
        }
        Spacer(modifier = Modifier.height(28.dp))
        if (isLastItem.not()) {
            HorizontalDivider(
                color = Colors.Main30,
                thickness = (6).dpToPx(context).toFloat(),
                dash = 16f
            )
        }
    }
}

@Composable
private fun RestStopInfo(
    modifier: Modifier = Modifier,
    title: String,
    desc: String
) {
    Row(modifier) {
        Text(
            text = title,
            style = Typo.BodyM14,
            color = Colors.Blk40
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = desc,
            style = Typo.BodySB14,
            color = Colors.Blk100
        )
    }
}

@Preview
@Composable
private fun RestStopItemPreview() {
    HyusikMatjuTheme {
        RestStopItem(
            restStop = TEST_REST_STOP.first(),
            isLastItem = false
        ) {

        }
    }
}