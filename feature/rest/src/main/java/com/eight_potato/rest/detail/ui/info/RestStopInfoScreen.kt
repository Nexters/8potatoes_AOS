package com.eight_potato.rest.detail.ui.info

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.designsystem.theme.Typo
import com.eight_potato.rest.R
import com.eight_potato.rest.detail.ui.common.RestStopContainer
import com.eight_potato.rest.detail.ui.common.RestStopHeader
import com.eight_potato.rest.detail.ui.common.RestStopRowItem
import com.eight_potato.rest.model.DetailRestStopUiModel
import com.eight_potato.rest.model.OperatingTimeUiModel
import com.eight_potato.rest.model.RestStopUiModel
import com.eight_potato.ui.util.AnnotatedTextBuilder

/**
 * 휴게소 정보 화면
 */
@Composable
internal fun RestStopInfoScreen(
    restStop: DetailRestStopUiModel,
    onCopyAddress: () -> Unit
) {
    Column (
        modifier = Modifier.verticalScroll(rememberScrollState())
    ){
        if (restStop.operatingTime.isNotEmpty()) {
            RestStopContainer {
                RestStopHeader(icon = R.drawable.ic_time, title = "영업시간")
                Spacer(modifier = Modifier.height(32.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    restStop.operatingTime.forEach {
                        RestStopOperatingTimeItem(operatingTime = it)
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(Colors.Blk5)
            )
        }
        if (restStop.brands.isNotEmpty()) {
            Column(modifier = Modifier.padding(vertical = 40.dp)) {
                RestStopHeader(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    icon = R.drawable.ic_brand,
                    title = "입점 브랜드"
                )
                Spacer(modifier = Modifier.height(32.dp))
                RestStopRowItem(brands = restStop.brands)
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(Colors.Blk5)
            )
        }
        if (restStop.amenities.isNotEmpty()) {
            Column(modifier = Modifier.padding(vertical = 40.dp)) {
                RestStopHeader(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    icon = R.drawable.ic_facility,
                    title = "편의시설"
                )
                Spacer(modifier = Modifier.height(32.dp))
                RestStopRowItem(brands = restStop.amenities)
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(Colors.Blk5)
            )
        }
        RestStopContainer {
            RestStopHeader(icon = R.drawable.ic_info, title = "기타 정보")
            Spacer(modifier = Modifier.height(32.dp))
            RestStopExtraInfo(
                icon = com.eight_potato.designsystem.R.drawable.ic_end,
                text = restStop.address
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onCopyAddress() },
                    painter = painterResource(id = R.drawable.ic_copy),
                    contentDescription = "",
                    tint = Colors.Main100
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            RestStopExtraInfo(
                icon = R.drawable.ic_call,
                text = restStop.callNumber
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(bottom = 164.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "본 정보는 특정 시점에 수집되어 실제 가격과 다를 수 있습니다.",
                style = Typo.SmallM12,
                color = Colors.Blk30
            )
            Text(
                text = AnnotatedTextBuilder.generateTextWithStatus(
                    text = "제공 한국도로공사",
                    highlightColor = Colors.Good100,
                    highlightedText = "한국도로공사"
                ),
                style = Typo.SmallM12,
                color = Colors.Blk30
            )
        }
    }
}

@Composable
private fun RestStopOperatingTimeItem(
    modifier: Modifier = Modifier,
    operatingTime: OperatingTimeUiModel
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = operatingTime.name,
            style = Typo.BodyM16,
            color = Colors.Blk60
        )
        Text(
            text = operatingTime.operatingTime,
            style = Typo.BodySB16,
            color = Colors.Blk100
        )
    }
}