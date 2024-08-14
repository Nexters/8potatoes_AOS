package com.eight_potato.rest.list.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
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
import com.eight_potato.ui.toolbar.ToolbarState
import com.eight_potato.ui.toolbar.rememberToolbarState

@Composable
internal fun RestListBottomSheet(
    modifier: Modifier = Modifier,
    configuration: Configuration = LocalConfiguration.current,
    restStops: List<RestStopUiModel> = TEST_REST_STOP,
    onClickRestStop: (RestStopUiModel) -> Unit
) {
    val screenHeight = configuration.screenHeightDp
    val lazyListState = rememberLazyListState()
    var toolbarRange by remember { mutableStateOf(0..(screenHeight * 2 / 3)) }

    val toolbarState: ToolbarState = rememberToolbarState(toolbarRange)

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                toolbarState.scrollTopLimitReached =
                    lazyListState.firstVisibleItemIndex == 0 && lazyListState.firstVisibleItemScrollOffset == 0
                toolbarState.scrollOffset = toolbarState.scrollOffset - available.y
                return Offset(x = 0f, y = toolbarState.consumed)
            }
        }
    }

    Column (
        modifier = modifier.nestedScroll(nestedScrollConnection)
    ) {
        Spacer(modifier = Modifier.height(toolbarState.height.dp))
        Column(
            modifier = modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_handle_bar),
                contentDescription = "",
                tint = Colors.Gray300
            )
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
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth().background(Colors.White),
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