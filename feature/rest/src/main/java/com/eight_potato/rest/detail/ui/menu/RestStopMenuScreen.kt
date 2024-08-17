package com.eight_potato.rest.detail.ui.menu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.eight_potato.designsystem.surface.HyusikSurface
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.designsystem.theme.Typo
import com.eight_potato.rest.R
import com.eight_potato.rest.detail.ui.common.RestStopContainer
import com.eight_potato.rest.detail.ui.common.RestStopHeader
import com.eight_potato.rest.model.RestStopUiModel
import com.eight_potato.rest.model.TEST_BRAND
import com.eight_potato.rest.model.TEST_REST_STOP
import com.eight_potato.ui.model.menu.MenuType
import com.eight_potato.ui.model.menu.MenuUiModel
import com.eight_potato.ui.model.menu.TEST_MENU
import org.junit.experimental.categories.Categories.CategoryFilter

/**
 * 휴게소 메뉴 정보 Tab
 */
@OptIn(ExperimentalFoundationApi::class)
internal fun LazyListScope.RestStopMenuScreen(
    menu: MenuUiModel,
    menus: Map<MenuType, List<MenuUiModel>>,
    restStop: RestStopUiModel
) {
    item {
        RestStopContainer {
            EssentialMenu(menu = menu)
        }
    }
    item {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(Colors.Blk5)
        )
    }
    item {
        RestStopHeader(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 40.dp, bottom = 12.dp),
            icon = R.drawable.ic_star,
            title = "전체 메뉴",
            desc = "${restStop.menuCount}개의 메뉴"
        )
    }
    stickyHeader {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = MenuType.values(), key = { it.ordinal }) {
                RestStopCategoryItem(category = it)
            }
        }
    }
    items(items = menus.keys.toList(), key = { it }) {
        RestStopMenu(category = it, menu = menus[it] ?: emptyList())
    }
}

@Composable
private fun RestStopCategoryItem(
    modifier: Modifier = Modifier,
    category: MenuType
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HyusikSurface(
            modifier = Modifier.size(64.dp),
            shape = CircleShape,
            contentPadding = PaddingValues(16.dp),
            border = BorderStroke(
                width = 1.dp,
                color = Colors.Blk5
            )
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = ""
            )
        }
        Text(
            text = category.text,
            style = Typo.BodyM14,
            color = Colors.Blk60
        )
    }
}
