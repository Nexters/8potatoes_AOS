package com.eight_potato.rest.detail.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.divider.HorizontalDivider
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.designsystem.theme.HyusikMatjuTheme
import com.eight_potato.designsystem.theme.Typo
import com.eight_potato.rest.R
import com.eight_potato.rest.model.RestStopUiModel
import com.eight_potato.ui.ext.fullFormat
import com.eight_potato.ui.ext.toMoneyFormat

internal fun LazyListScope.RestStopFuelBody(
    restStop: RestStopUiModel
) {
    item {
        RestStopFuelContainer {
            RestStopFuelHeader(
                icon = R.drawable.ic_folk,
                title = "주유 및 충전"
            )
            Spacer(modifier = Modifier.height(26.dp))
            Row {
                RestStopFuelItem(title = "휘발유", price = restStop.gasolinePrice)
                RestStopFuelItem(title = "경유", price = restStop.dieselPrice)
                RestStopFuelItem(title = "LPG", price = restStop.lpgPrice)
            }
            Spacer(modifier = Modifier.height(26.dp))
            RestStopChargeItem(title = "전기차 충전", isAble = restStop.existChargeElectricCar)
            Spacer(modifier = Modifier.height(20.dp))
            RestStopChargeItem(title = "수소차 충전", isAble = restStop.existChargeHydrogenCar)
        }
    }
    item {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(Colors.Blk5)
        )
    }
    item {
        RestStopFuelContainer {
            RestStopFuelHeader(
                icon = R.drawable.ic_star,
                title = "주차 공간",
                desc = "${restStop.totalParkArea}대 주차 가능"
            )
            Spacer(modifier = Modifier.height(26.dp))
            Row {
                RestStopFuelItem(title = "소형", price = restStop.compatCarParkArea, postFix = "대")
                RestStopFuelItem(title = "대형", price = restStop.largeCarParkArea, postFix = "대")
                RestStopFuelItem(
                    title = "장애인",
                    price = restStop.disabledPersonParkArea,
                    postFix = "대"
                )
            }
        }
    }
    item {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(bottom = 60.dp)
        ) {
            Text(
                text = "${restStop.updateDate.fullFormat()} 업데이트",
                style = Typo.SmallM12,
                color = Colors.Blk30
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "본 정보는 특정 시점에 수집되어 실제와 다를 수 있습니다.",
                style = Typo.SmallM12,
                color = Colors.Blk30
            )
            Spacer(modifier = Modifier.height(26.dp))
        }
    }
}

@Composable
private fun RestStopFuelContainer(
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier.padding(vertical = 40.dp, horizontal = 20.dp),
        content = content
    )
}

@Composable
private fun RestStopFuelHeader(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    title: String,
    desc: String? = null
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
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

@Composable
private fun RowScope.RestStopFuelItem(
    modifier: Modifier = Modifier,
    title: String,
    price: Int,
    postFix: String = "원"
) {
    if (price < 1) return
    Column(
        modifier = modifier
            .weight(1f)
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = title,
            style = Typo.BodyM16,
            color = Colors.Blk60
        )
        Text(
            text = "${price.toMoneyFormat()}$postFix",
            style = Typo.HeadB18,
            color = Colors.Blk100
        )
    }
}

@Composable
private fun RestStopChargeItem(
    modifier: Modifier = Modifier,
    title: String,
    isAble: Boolean
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = Typo.BodyM16,
            color = Colors.Blk60
        )
        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .padding(
                    start = 16.dp,
                    end = if (isAble) 26.dp else 12.dp
                ),
            color = Colors.Blk10,
            dash = 8f
        )
        Text(
            text = if (isAble) "가능" else "불가능",
            style = Typo.BodySB16,
            color = Colors.Blk100
        )
    }
}

@Preview
@Composable
private fun RestStopFuelItemPreview() {
    HyusikMatjuTheme {
        Row {
            RestStopFuelItem(
                title = "휘발유",
                price = 1234
            )
        }
    }
}

@Preview
@Composable
private fun RestStopChargeItemPreview() {
    HyusikMatjuTheme {
        RestStopChargeItem(title = "전기차 충전", isAble = true)
    }
}