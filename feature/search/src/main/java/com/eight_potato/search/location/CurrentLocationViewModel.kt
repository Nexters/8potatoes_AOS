package com.eight_potato.search.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eight_potato.domain.model.address.GetAddressByPoiUseCase
import com.eight_potato.search.model.SimpleAddressUiModel
import com.eight_potato.search.model.toUiModel
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 현재 위치 검색 화면 ViewModel
 */
@HiltViewModel
class CurrentLocationViewModel @Inject constructor(
    private val getAddressByPoiUseCase: GetAddressByPoiUseCase
) : ViewModel() {
    private val _currentAddress = MutableStateFlow<SimpleAddressUiModel?>(null)
    val currentAddress: StateFlow<SimpleAddressUiModel?> = _currentAddress.asStateFlow()

    private val _uiEvent = MutableSharedFlow<CurrentLocationUiEvent>()
    val uiEvent: SharedFlow<CurrentLocationUiEvent> = _uiEvent.asSharedFlow()

    fun getAddress(position: LatLng) {
        viewModelScope.launch {
            getAddressByPoiUseCase(position.latitude, position.longitude)
                .onSuccess {
                    println(it)
                    _currentAddress.value = it.toUiModel()
                }.onFailure {
                    println(it)
                    _uiEvent.emit(CurrentLocationUiEvent.FailGetAddress)
                }
        }
    }
}

sealed interface CurrentLocationUiEvent {
    object FailGetAddress : CurrentLocationUiEvent
}