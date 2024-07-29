package com.eight_potato.rest.list.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.ui.toolbar.ToolbarState
import com.eight_potato.ui.toolbar.rememberToolbarState

@Composable
internal fun RestListBottomSheet(
    configuration: Configuration = LocalConfiguration.current,
    modifier: Modifier = Modifier,
    restStops: List<String> = (0..20).map { "$it" }
) {
    val screenHeight = configuration.screenHeightDp
    val lazyListState = rememberLazyListState()
    var toolbarRange by remember { mutableStateOf(0..(screenHeight * 2 / 3)) }
    val toolbarState: ToolbarState = rememberToolbarState(toolbarRange)

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                toolbarState.scrollTopLimitReached =
                    lazyListState.firstVisibleItemIndex == 0 && lazyListState.firstVisibleItemScrollOffset == 0 &&
                            lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index != restStops.lastIndex
                toolbarState.scrollOffset = toolbarState.scrollOffset - available.y
                return Offset(x = 0f, y = toolbarState.consumed)
            }
        }
    }

    Column(
        modifier = modifier.nestedScroll(nestedScrollConnection)
    ) {
        Spacer(modifier = Modifier.height(toolbarState.height.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Colors.White,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                ).padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "총 ${restStops.size}개의 휴게소",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Layout(
            modifier = Modifier,
            content = {
                LazyColumn(
                    modifier = Modifier
                        .layoutId("list")
                        .fillMaxWidth()
                        .background(Colors.White),
                    state = lazyListState
                ) {
                    items(items = restStops, key = { it }) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            text = it.toString()
                        )
                    }
                }
            }
        ) { measurables, constraints ->
            val list = measurables.find { it.layoutId == "list" }!!.measure(constraints)

            layout(
                width = constraints.maxWidth,
                height = constraints.maxHeight
            ) {
                list.placeRelative(0, 0)
            }
        }
    }
}