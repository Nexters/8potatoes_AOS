package com.eight_potato.rest.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.eight_potato.rest.model.RestStopUiModel
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

    // 탭 변경
    fun changeTab(
        tab: RestStopDetailActivity.Companion.RestStopTab
    ) {
        _currentTab.value = tab
    }
}