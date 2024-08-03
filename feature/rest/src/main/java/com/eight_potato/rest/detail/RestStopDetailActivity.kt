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
import com.eight_potato.designsystem.divider.HorizontalDivider
import com.eight_potato.rest.detail.ui.RestStopDetailHeader
import com.eight_potato.rest.detail.ui.RestStopDetailTab
import com.eight_potato.rest.detail.ui.menu.RestStopMenuScreen
import com.eight_potato.rest.model.TEST_REST_STOP
import com.eight_potato.ui.base.BaseActivity
import com.eight_potato.ui.ext.pxToDp
import com.eight_potato.ui.header.SingleTextHeader
import com.eight_potato.ui.model.menu.TEST_MENU

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
                            onClickTab = viewModel::changeTab
                        )
                    }
                }
                HorizontalDivider(
                    thickness = (2).pxToDp(this@RestStopDetailActivity),
                    color = Color(0xFFF4F0EA),
                )
                
                when (currentTab.value) {
                    RestStopTab.MENU -> {
                        RestStopMenuScreen(
                            menu = TEST_MENU.first(),
                            restStop = restStop,
                            onClickMoreMenu = {}
                        )
                    }
                    RestStopTab.EXTRA -> {

                    }
                    RestStopTab.INFO -> {}
                }
            }
        }
    }

    companion object {
        enum class RestStopTab(
            val text: String
        ) {
            MENU( "먹거리"), // 휴게소 메뉴 정보
            EXTRA("주유 / 주차"), // 주유&주차 정보
            INFO("기타정보",) // 휴게소 정보
        }
    }
}