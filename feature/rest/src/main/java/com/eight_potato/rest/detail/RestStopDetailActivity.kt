package com.eight_potato.rest.detail

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.rest.detail.ui.common.RestSTopDetailTab
import com.eight_potato.rest.detail.ui.common.RestStopDetailHeader
import com.eight_potato.rest.detail.ui.extra.RestStopFuelBody
import com.eight_potato.rest.detail.ui.info.RestStopInfoScreen
import com.eight_potato.rest.detail.ui.menu.RestStopMenuScreen
import com.eight_potato.ui.base.BaseActivity
import com.eight_potato.ui.ext.dpToPx
import com.eight_potato.ui.ext.pxToDp
import com.eight_potato.ui.ext.spToPx
import com.eight_potato.ui.header.SingleTextHeader
import com.eight_potato.ui.toolbar.HyusikLargeTopAppBar
import com.eight_potato.ui.toolbar.HyusikTopAppBarColors
import dagger.hilt.android.AndroidEntryPoint

/**
 * 휴게소 상세 화면 Activity
 */
@AndroidEntryPoint
class RestStopDetailActivity : BaseActivity() {
    private val viewModel : RestStopDetailViewModel by viewModels()

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    override fun Body() {
        val restStop = viewModel.restStop.collectAsState()
        val recommendedMenus = viewModel.recommendedMenus.collectAsState()
        val topAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

        Scaffold (
            modifier = Modifier.nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
            topBar = {
                restStop.value?.let {
                    HyusikLargeTopAppBar(
                        title = {
                            RestStopDetailHeader(restStop = it)
                        },
                        smallTitle = {
                            SingleTextHeader(
                                title = it.name,
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
                }
            }
        ) { padding ->
            val currentTab = viewModel.currentTab.collectAsState()
            val clipboardManager = LocalClipboardManager.current

            val menuTypes = viewModel.menuTypes.collectAsState()
            val currentMenuType = viewModel.currentMenuType.collectAsState()
            val groupedMenus = viewModel.groupedMenus.collectAsState()

            val menuScrollState = rememberLazyListState()

            LaunchedEffect(key1 = currentMenuType.value) {
                if (viewModel.isChangedByTabSelect) {
                    val currentMenuTypePosition = menuTypes.value.indexOf(currentMenuType.value)
                    menuScrollState.animateScrollToItem(
                        currentMenuTypePosition + 2,
                        - (116).dpToPx(this@RestStopDetailActivity) - (14).spToPx(this@RestStopDetailActivity)
                    )
                }
            }

            LaunchedEffect(key1 = menuScrollState.firstVisibleItemIndex) {
                if (viewModel.isChangedByTabSelect.not()) {
                    if (menuScrollState.firstVisibleItemIndex > 1) {
                        viewModel.changeMenuType(
                            menuTypes.value.get(menuScrollState.firstVisibleItemIndex - 2),
                            false
                        )
                    }
                } else {
                    viewModel.isChangedByTabSelect = false
                }
            }

            Column (
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                RestSTopDetailTab(
                    currentTab = currentTab.value,
                    onClickTab = viewModel::changeTab
                )
                restStop.value?.let {
                    when (currentTab.value) {
                        RestStopTab.MENU -> {
                            RestStopMenuScreen(
                                scrollState = menuScrollState,
                                menu = recommendedMenus.value,
                                menuTypes = menuTypes.value,
                                menus = groupedMenus.value,
                                restStop = it,
                                currentMenuType = currentMenuType.value,
                                onClickMenuTab = {
                                    viewModel.changeMenuType(it, true)
                                }
                            )
                        }

                        RestStopTab.EXTRA -> RestStopFuelBody(restStop = it)
                        RestStopTab.INFO -> RestStopInfoScreen(
                            restStop = it,
                            onCopyAddress = {
                                clipboardManager.setText(AnnotatedString(it.address))
                            }
                        )
                    }
                }
            }
        }
    }

    companion object {
        const val REST_STOP_ARGS = "restStopCode"

        enum class RestStopTab(
            val text: String
        ) {
            MENU("먹거리"), // 휴게소 메뉴 정보
            EXTRA("주유 • 주차"), // 주유&주차 정보
            INFO("기타정보") // 휴게소 정보
        }

        fun getIntent(
            context: Context,
            restStopCode: String
        ) = Intent(context, RestStopDetailActivity::class.java).apply {
            putExtra(REST_STOP_ARGS, restStopCode)
        }
    }
}