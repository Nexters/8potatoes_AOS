package com.eight_potato.rest.detail.ui.menu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.surface.HyusikSurface
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.designsystem.theme.Typo
import com.eight_potato.rest.R
import com.eight_potato.rest.detail.ui.common.RestStopContainer
import com.eight_potato.rest.detail.ui.common.RestStopHeader
import com.eight_potato.rest.model.DetailRestStopUiModel
import com.eight_potato.ui.model.menu.MenuType
import com.eight_potato.ui.model.menu.MenuUiModel
import com.eight_potato.ui.model.menu.RecommendMenuUIModel

/**
 * 휴게소 메뉴 정보 Tab
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun RestStopMenuScreen(
    scrollState: LazyListState,
    menu: List<RecommendMenuUIModel>,
    menuTypes: List<MenuType>,
    menus: Map<MenuType, List<MenuUiModel>>,
    restStop: DetailRestStopUiModel,
    currentMenuType: MenuType,
    onClickMenuTab: (MenuType) -> Unit
) {
    LazyColumn (
        state = scrollState
    ){
        item {
            Column {
                if (menu.isNotEmpty()) {
                    RestStopContainer {
                        EssentialMenu(menu = menu)
                    }
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .background(Colors.Blk5)
                    )
                }
                RestStopHeader(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 40.dp, bottom = 12.dp),
                    icon = R.drawable.ic_all_menu,
                    title = "전체 메뉴",
                    desc = "${restStop.menuCount}개의 메뉴"
                )
            }
        }
        stickyHeader {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Colors.White),
                contentPadding = PaddingValues(20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(items = menuTypes, key = { it.ordinal }) {
                    RestStopCategoryItem(
                        category = it,
                        isCurrentType = currentMenuType == it,
                        onClickMenuTab = onClickMenuTab
                    )
                }
            }
        }
        items(items = menus.keys.toList(), key = { it }) {
            RestStopMenu(category = it, menu = menus[it] ?: emptyList())
        }
    }
}

@Composable
private fun RestStopCategoryItem(
    modifier: Modifier = Modifier,
    category: MenuType,
    isCurrentType: Boolean = false,
    onClickMenuTab: (MenuType) -> Unit
) {
    Column(
        modifier = modifier.clickable { onClickMenuTab(category) },
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HyusikSurface(
            modifier = Modifier.size(64.dp),
            shape = CircleShape,
            border = BorderStroke(
                width = 1.dp,
                color = if (isCurrentType) Colors.Main100 else Colors.Blk5
            )
        ) {
            category.icon?.let {
                Image(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(id = it),
                    contentDescription = ""
                )
            }
        }
        Text(
            text = category.text,
            style = Typo.BodyM14,
            color = if (isCurrentType) Colors.Main100 else Colors.Blk60
        )
    }
}
