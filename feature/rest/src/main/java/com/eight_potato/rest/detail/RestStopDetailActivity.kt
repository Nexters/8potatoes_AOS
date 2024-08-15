package com.eight_potato.rest.detail

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.eight_potato.rest.detail.ui.RestSTopDetailTab
import com.eight_potato.rest.detail.ui.RestStopDetailHeader
import com.eight_potato.rest.detail.ui.RestStopFuelBody
import com.eight_potato.rest.detail.ui.menu.RestStopMenuScreen
import com.eight_potato.rest.model.RestStopUiModel
import com.eight_potato.rest.model.TEST_REST_STOP
import com.eight_potato.ui.base.BaseActivity
import com.eight_potato.ui.header.SingleTextHeader
import com.eight_potato.ui.model.menu.TEST_MENU

/**
 * 휴게소 상세 화면 Activity
 */
class RestStopDetailActivity : BaseActivity() {
    private val viewModel: RestStopDetailViewModel by viewModels()

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Body() {
        var headerTitle by remember { mutableStateOf("") }
        val restStop = TEST_REST_STOP.first()
        val currentTab = viewModel.currentTab.collectAsState()
        val scroll = rememberScrollState()

        Scaffold(
            topBar = {
                SingleTextHeader(
                    title = headerTitle,
                    onClickCloseButton = { finish() }
                )
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier.padding(padding)
            ) {
                item { RestStopDetailHeader(restStop = restStop) }
                stickyHeader {
                    RestSTopDetailTab(
                        currentTab = currentTab.value,
                        onClickTab = viewModel::changeTab
                    )
                }
                when(currentTab.value) {
                    RestStopTab.MENU -> {
                        RestStopMenuScreen(
                            menu = TEST_MENU.first(),
                            restStop = restStop
                        )
                    }
                    RestStopTab.EXTRA -> RestStopFuelBody(restStop = restStop)
                    RestStopTab.INFO -> TODO()
                }
            }
        }
    }

    companion object {
        const val REST_STOP_ARGS = "restStop"

        enum class RestStopTab(
            val text: String
        ) {
            MENU("먹거리"), // 휴게소 메뉴 정보
            EXTRA("주유 • 주차"), // 주유&주차 정보
            INFO("기타정보") // 휴게소 정보
        }

        fun getIntent(
            context: Context,
            restStop: RestStopUiModel
        ) = Intent(context, RestStopDetailActivity::class.java).apply {
            putExtra(REST_STOP_ARGS, restStop)
        }
    }
}