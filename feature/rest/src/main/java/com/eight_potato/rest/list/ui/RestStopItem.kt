package com.eight_potato.rest.list.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
                color = Colors.White,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 20.dp)
            .padding(top = 28.dp)
    ) {
        Text(
            text = restStop.direction,
            fontSize = 12.sp,
            color = Color(0xFFFF7512),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = restStop.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(12.dp),
                        painter = painterResource(id = com.eight_potato.designsystem.R.drawable.ic_end),
                        contentDescription = "",
                        tint = Color(0xFFFF7512)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = restStop.address,
                        fontSize = 14.sp,
                        color = Color(0xFFA8A8A8)
                    )
                }
            }
            HyusikChip(
                shape = RoundedCornerShape(8.dp),
                paddingValues = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                backgroundColor = Color(0xFFF1FAC3)
            ) {
                Text(
                    text = restStop.state.text,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF4F9D12)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFFFAFAFA),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 8.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(12.dp),
                    imageVector = Icons.Default.Star,
                    contentDescription = "",
                    tint = Color(0xFFFED402)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = restStop.rate.toString(),
                    color = Color(0xFFFF7512),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            RestStopInfo(
                title = "휘발유",
                desc = "${restStop.gasolinePrice.toMoneyFormat()}원"
            )
            RestStopInfo(
                title = "경유",
                desc = "${restStop.dieselPrice.toMoneyFormat()}원"
            )
            Spacer(
                modifier = Modifier
                    .width(1.dp)
                    .background(Color(0xFFBCB6AD))
            )
            RestStopInfo(title = "총 메뉴", desc = "${restStop.menuCount}가지")
        }
        Spacer(modifier = Modifier.height(28.dp))
        if (isLastItem.not()) {
            HorizontalDivider(
                color = Color(0xFFEDF1F3),
                thickness = (3).dpToPx(context).toFloat(),
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
            color = Color(0xFFA8A8A8),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = desc,
            color = Colors.Black,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
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