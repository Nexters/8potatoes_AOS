package com.eight_potato.ui.direction

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.eight_potato.ui.model.address.AddressUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DirectionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _start = MutableStateFlow<AddressUiModel?>(savedStateHandle[DirectionActivity.START_ARGS])
    val start: StateFlow<AddressUiModel?> = _start.asStateFlow()

    private val _end = MutableStateFlow<AddressUiModel?>(savedStateHandle[DirectionActivity.END_ARGS])
    val end: StateFlow<AddressUiModel?> = _end.asStateFlow()

    fun setStart(address: AddressUiModel) {
        _start.value = address
    }

    fun setEnd(address: AddressUiModel) {
        _end.value = address
    }

    fun reverse() {
        val temp = start.value
        _start.value = end.value
        _end.value = temp
    }
}