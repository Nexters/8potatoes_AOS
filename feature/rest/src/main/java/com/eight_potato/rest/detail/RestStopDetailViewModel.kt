package com.eight_potato.rest.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.eight_potato.rest.model.RestStopUiModel
import com.eight_potato.ui.model.menu.MenuType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RestStopDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    // 휴게소 정보
    val restStop: RestStopUiModel? = savedStateHandle[RestStopDetailActivity.REST_STOP_ARGS]

    // 현재 상단
    private val _currentTab = MutableStateFlow(RestStopDetailActivity.Companion.RestStopTab.MENU)
    val currentTab: StateFlow<RestStopDetailActivity.Companion.RestStopTab> = _currentTab.asStateFlow()

    // 현재 메뉴 탭
    private val _currentMenuType = MutableStateFlow(MenuType.RECOMMEND)
    val currentMenuType: StateFlow<MenuType> = _currentMenuType.asStateFlow()

    // 탭 변경
    fun changeTab(
        tab: RestStopDetailActivity.Companion.RestStopTab
    ) {
        _currentTab.value = tab
    }

    // 메뉴 타입 변경
    fun changeMenuType(
        tab: MenuType
    ) {
        _currentMenuType.value = tab
    }
}