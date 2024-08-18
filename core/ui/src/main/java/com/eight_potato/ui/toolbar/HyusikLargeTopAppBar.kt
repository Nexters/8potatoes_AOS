package com.eight_potato.ui.toolbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.theme.Typo

private val LargeTitleBottomPadding = 28.dp

@ExperimentalMaterial3Api
@Composable
fun HyusikLargeTopAppBar(
    title: @Composable () -> Unit,
    smallTitle: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    colors: HyusikTopAppBarColors,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    HyusikTwoRowsTopAppBar(
        title = title,
        titleTextStyle = Typo.BodySB18,
        smallTitleTextStyle = Typo.BodyM14,
        titleBottomPadding = LargeTitleBottomPadding,
        smallTitle = smallTitle,
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        colors = colors,
        windowInsets = windowInsets,
        maxHeight = 152.dp,
        pinnedHeight = 64.dp,
        scrollBehavior = scrollBehavior
    )
}