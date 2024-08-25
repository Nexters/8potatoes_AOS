package com.eight_potato.rest.detail.ui.extra

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.divider.HorizontalDivider
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.designsystem.theme.HyusikMatjuTheme
import com.eight_potato.designsystem.theme.Typo
import com.eight_potato.rest.R
import com.eight_potato.rest.detail.ui.common.RestStopContainer
import com.eight_potato.rest.detail.ui.common.RestStopHeader
import com.eight_potato.rest.model.DetailRestStopUiModel
import com.eight_potato.ui.ext.toMoneyFormat

@Composable
internal fun RestStopFuelBody(
    restStop: DetailRestStopUiModel
) {
    Column (
        modifier = Modifier.verticalScroll(rememberScrollState())
    ){
        RestStopContainer {
            RestStopHeader(
                icon = R.drawable.ic_gas,
                title = "주유 및 충전"
            )
            Spacer(modifier = Modifier.height(26.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                RestStopFuelItem(title = "휘발유", price = restStop.gasolinePrice)
                RestStopFuelItem(title = "경유", price = restStop.dieselPrice)
                RestStopFuelItem(title = "LPG", price = restStop.lpgPrice)
            }
            Spacer(modifier = Modifier.height(26.dp))
            RestStopChargeItem(title = "전기차 충전", isAble = restStop.existChargeElectricCar)
            Spacer(modifier = Modifier.height(20.dp))
            RestStopChargeItem(title = "수소차 충전", isAble = restStop.existChargeHydrogenCar)
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(Colors.Blk5)
        )
        RestStopContainer {
            RestStopHeader(
                icon = R.drawable.ic_park,
                title = "주차 공간",
                desc = "${restStop.totalParkArea}대 주차 가능"
            )
            Spacer(modifier = Modifier.height(26.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                RestStopFuelItem(title = "소형", price = restStop.compatCarParkArea, postFix = "대")
                RestStopFuelItem(title = "대형", price = restStop.largeCarParkArea, postFix = "대")
                RestStopFuelItem(
                    title = "장애인",
                    price = restStop.disabledPersonParkArea,
                    postFix = "대"
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(bottom = 60.dp)
        ) {
            Text(
                text = "${restStop.updateDate} 업데이트",
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
private fun RowScope.RestStopFuelItem(
    modifier: Modifier = Modifier,
    title: String,
    price: Any?,
    postFix: String = ""
) {
    if (price == null) return
    Column(
        modifier = modifier
            .weight(1f)
            .background(
                color = Colors.Main10,
                shape = RoundedCornerShape(16.dp)
            )
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
            text = "${if (price is Int) price.toMoneyFormat() else price}$postFix",
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
                price = "1,2345"
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