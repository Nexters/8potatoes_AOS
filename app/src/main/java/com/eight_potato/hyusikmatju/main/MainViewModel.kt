package com.eight_potato.hyusikmatju.main

import androidx.lifecycle.ViewModel
import com.eight_potato.ui.model.address.AddressUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel(){
    private val _start = MutableStateFlow<AddressUiModel?>(null)
    val start: StateFlow<AddressUiModel?> = _start.asStateFlow()

    private val _end = MutableStateFlow<AddressUiModel?>(null)
    val end: StateFlow<AddressUiModel?> = _end.asStateFlow()

    fun setStart(address: AddressUiModel) {
        _start.value = address
    }

    fun setEnd(address: AddressUiModel) {
        _end.value = address
    }

    fun reverse(){
        val temp = start.value
        _start.value = end.value
        _end.value = temp
    }
}