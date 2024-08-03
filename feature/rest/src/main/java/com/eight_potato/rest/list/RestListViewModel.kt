package com.eight_potato.rest.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eight_potato.domain.usecase.direction.GetDirectionUseCase
import com.eight_potato.ui.model.address.AddressUiModel
import com.eight_potato.ui.model.address.PoiUiModel
import com.eight_potato.ui.model.address.toModel
import com.eight_potato.ui.model.address.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 경로 상의 휴게소 리스트를 볼 수 있는 화면 Activity
 */
@HiltViewModel
class RestListViewModel @Inject constructor(
    private val getDirectionUseCase: GetDirectionUseCase
) : ViewModel() {
    private val _path = MutableStateFlow<List<PoiUiModel>>(emptyList())
    val path: StateFlow<List<PoiUiModel>> = _path.asStateFlow()

    fun getDirection(
        start: AddressUiModel?,
        end: AddressUiModel?
    ) {
        viewModelScope.launch {
            val startPoi = start?.poi ?: return@launch
            val endPoi = end?.poi ?: return@launch
            getDirectionUseCase(
                start = startPoi.toModel(),
                end = endPoi.toModel()
            ).onSuccess {
                _path.value = it.path.map { poi -> poi.toUiModel() }
            }.onFailure {
                println(it)
            }
        }
    }
}