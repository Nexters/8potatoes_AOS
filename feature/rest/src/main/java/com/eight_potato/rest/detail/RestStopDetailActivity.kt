package com.eight_potato.rest.detail

import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.eight_potato.rest.detail.ui.RestStopDetailHeader
import com.eight_potato.rest.detail.ui.RestStopDetailTab
import com.eight_potato.rest.model.TEST_REST_STOP
import com.eight_potato.ui.base.BaseActivity
import com.eight_potato.ui.header.SingleTextHeader

/**
 * 휴게소 상세 화면 Activity
 */
class RestStopDetailActivity : BaseActivity() {
    private val viewModel: RestStopDetailViewModel by viewModels()

    @Composable
    override fun Body() {
        Scaffold(
            topBar = {
                SingleTextHeader(
                    title = "검색 결과",
                    onClickCloseButton = { finish() }
                )
            }
        ) {
            val restStop = TEST_REST_STOP.first()
            val currentTab = viewModel.currentTab.collectAsState()
            val scroll = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF8F9FA))
                    .padding(it)
                    .verticalScroll(scroll)
            ) {
                RestStopDetailHeader(restStop = restStop)
                Row(modifier = Modifier.fillMaxWidth()) {
                    RestStopTab.values().forEach { tab ->
                        RestStopDetailTab(
                            tab = tab,
                            isCurrentStep = currentTab.value == tab,
                            shape = if (tab == currentTab.value) tab.selectShape
                            else tab.unSelectShape,
                            onClickTab = viewModel::changeTab
                        )
                    }
                }
            }
        }
    }

    companion object {
        enum class RestStopTab(
            val text: String,
            val selectShape: Shape,
            val unSelectShape: Shape
        ) {
            MENU(
                "맛있는 먹거리",
                RoundedCornerShape(topEnd = 20.dp),
                RoundedCornerShape(bottomEnd = 20.dp)
            ), // 휴게소 메뉴 정보
            INFO(
                "기타 정보",
                RoundedCornerShape(topStart = 20.dp),
                RoundedCornerShape(bottomStart = 20.dp)
            ) // 휴게소 정보
        }
    }
}