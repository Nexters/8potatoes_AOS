package com.eight_potato.rest.list.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.designsystem.theme.HyusikMatjuTheme
import com.eight_potato.designsystem.theme.Typo
import com.eight_potato.rest.model.RestStopUiModel
import com.eight_potato.rest.model.TEST_REST_STOP

@Composable
internal fun RestListBottomSheet(
    modifier: Modifier = Modifier,
    restStops: List<RestStopUiModel> = TEST_REST_STOP,
    onClickRestStop: (RestStopUiModel) -> Unit
) {
    Column (
        modifier = modifier.fillMaxWidth().background(Colors.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "전국 휴게소의 주차 시설 및 화장실은 24시간 이용 가능합니다.",
            style = Typo.SmallM12,
            color = Colors.neutral
        )
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .background(Colors.White),
            contentPadding = PaddingValues(20.dp)
        ) {
            itemsIndexed(items = restStops, key = { index, _ -> index }) { index, item ->
                RestStopItem(
                    restStop = item,
                    isLastItem = index == restStops.lastIndex,
                    onClickRestStop = onClickRestStop
                )
            }
        }
    }
}

@Preview
@Composable
private fun RestListBottomSheetPreview() {
    HyusikMatjuTheme {
        RestListBottomSheet {

        }
    }
}