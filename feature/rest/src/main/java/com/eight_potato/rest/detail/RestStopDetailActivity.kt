package com.eight_potato.rest.detail

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.designsystem.theme.Typo
import com.eight_potato.rest.detail.ui.common.RestSTopDetailTab
import com.eight_potato.rest.detail.ui.common.RestStopDetailHeader
import com.eight_potato.rest.detail.ui.common.RestStopHeader
import com.eight_potato.rest.detail.ui.extra.RestStopFuelBody
import com.eight_potato.rest.detail.ui.info.RestStopInfoScreen
import com.eight_potato.rest.detail.ui.menu.RestStopMenuScreen
import com.eight_potato.rest.model.RestStopUiModel
import com.eight_potato.rest.model.TEST_REST_STOP
import com.eight_potato.ui.R
import com.eight_potato.ui.base.BaseActivity
import com.eight_potato.ui.header.SingleTextHeader
import com.eight_potato.ui.model.menu.TEST_MENU
import com.eight_potato.ui.toolbar.HyusikLargeTopAppBar
import com.eight_potato.ui.toolbar.HyusikTopAppBarColors

/**
 * 휴게소 상세 화면 Activity
 */
class RestStopDetailActivity : BaseActivity() {
    private val viewModel : RestStopDetailViewModel by viewModels()

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    override fun Body() {
        val restStop = TEST_REST_STOP.first()
        val topAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

        Scaffold (
            modifier = Modifier.nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
            topBar = {
                HyusikLargeTopAppBar(
                    title = {
                        RestStopDetailHeader(restStop = restStop)
                    },
                    smallTitle = {
                        SingleTextHeader(
                            title = restStop.name,
                            onClickCloseButton = { finish() }
                        )
                    },
                    colors = HyusikTopAppBarColors(
                        containerColor = Colors.White,
                        scrolledContainerColor = Colors.White,
                        navigationIconContentColor = Colors.White,
                        titleContentColor = Colors.Blk100,
                        actionIconContentColor = Colors.Main100
                    ),
                    scrollBehavior = topAppBarScrollBehavior
                )
//                LargeTopAppBar(
//                    scrollBehavior = topAppBarScrollBehavior,
//                    title = {
//                        RestStopDetailHeader(restStop = restStop)
//                    },
//                    navigationIcon = {
//                        Icon(
//                            modifier = Modifier
//                                .padding(horizontal = 12.dp, vertical = 16.dp)
//                                .size(32.dp)
//                                .clickable { finish() },
//                            painter = painterResource(id = R.drawable.ic_arrow_left),
//                            contentDescription = "이전화면으로 이동"
//                        )
//                    }
//                )
            }
        ) { padding ->
            val currentTab = viewModel.currentTab.collectAsState()
            val clipboardManager = LocalClipboardManager.current
            val currentMenuType = viewModel.currentMenuType.collectAsState()
            val groupedMenus = TEST_MENU.groupBy { it.menuType }

            Column {
                RestSTopDetailTab(
                    currentTab = currentTab.value,
                    onClickTab = viewModel::changeTab
                )
                LazyColumn(
                    modifier = Modifier.padding(padding)
                ) {
                    when (currentTab.value) {
                        RestStopTab.MENU -> {
                            RestStopMenuScreen(
                                menu = TEST_MENU.first(),
                                menus = groupedMenus,
                                restStop = restStop,
                                currentMenuType = currentMenuType.value,
                                onClickMenuTab = viewModel::changeMenuType
                            )
                        }

                        RestStopTab.EXTRA -> RestStopFuelBody(restStop = restStop)
                        RestStopTab.INFO -> RestStopInfoScreen(
                            restStop = restStop,
                            onCopyAddress = {
                                clipboardManager.setText(AnnotatedString(restStop.address))
                            }
                        )
                    }
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