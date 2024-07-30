package com.eight_potato.rest.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.eight_potato.ui.model.address.AddressUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * 경로 상의 휴게소 리스트를 볼 수 있는 화면 Activity
 */
@HiltViewModel
class RestListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
}