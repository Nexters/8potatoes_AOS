package com.eight_potato.rest.detail.ui.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eight_potato.rest.model.RestStopUiModel
import com.eight_potato.rest.model.TEST_BRAND
import com.eight_potato.rest.model.TEST_REST_STOP
import com.eight_potato.ui.model.menu.MenuUiModel
import com.eight_potato.ui.model.menu.TEST_MENU

/**
 * 휴게소 메뉴 정보 Tab
 */
@Composable
internal fun RestStopMenuScreen(
    modifier: Modifier = Modifier,
    menu: MenuUiModel,
    restStop: RestStopUiModel,
    onClickMoreMenu: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        EssentialMenu(
            menu = menu,
            restStopName = restStop.name
        )
        Spacer(modifier = Modifier.height(8.dp))
        RestStopMenu(
            menus = TEST_MENU.subList(0,4),
            onClickMore = onClickMoreMenu,
            onChangeMenuSortType = {}
        )
        Spacer(modifier = Modifier.height(8.dp))
        RestStopBrand(brands = TEST_BRAND)
    }
}