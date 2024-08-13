package com.eight_potato.rest.list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.designsystem.theme.HyusikMatjuTheme
import com.eight_potato.designsystem.theme.Typo
import com.eight_potato.rest.R
import com.eight_potato.rest.model.RestStopUiModel
import com.eight_potato.rest.model.TEST_REST_STOP

@Composable
internal fun RestListBottomSheet(
    modifier: Modifier = Modifier,
    restStops: List<RestStopUiModel> = TEST_REST_STOP,
    onClickRestStop: (RestStopUiModel) -> Unit
) {
    Column(
        modifier = modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(painter = painterResource(R.drawable.ic_handle_bar), contentDescription = "", tint = Colors.Gray300)
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            text = "총 ${restStops.size}개의 휴게소를 들릴 수 있어요",
            textAlign = TextAlign.Center,
            style = Typo.HeadB18
        )
        Text(
            text = "전국 휴게소의 주차 시설 및 화장실은 24시간 이용 가능합니다.",
            style = Typo.SmallM12,
            color = Colors.neutral
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
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