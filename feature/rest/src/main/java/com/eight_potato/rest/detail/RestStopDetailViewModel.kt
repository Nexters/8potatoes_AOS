package com.eight_potato.rest.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RestStopDetailViewModel @Inject constructor(): ViewModel() {
    private val _currentTab = MutableStateFlow(RestStopDetailActivity.Companion.RestStopTab.MENU)
    val currentTab: StateFlow<RestStopDetailActivity.Companion.RestStopTab> = _currentTab.asStateFlow()

    fun changeTab(tab: RestStopDetailActivity.Companion.RestStopTab) {
        _currentTab.value = tab
    }
}