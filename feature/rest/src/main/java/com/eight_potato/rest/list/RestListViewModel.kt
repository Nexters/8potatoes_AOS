package com.eight_potato.rest.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eight_potato.domain.model.direction.HighwayModel
import com.eight_potato.domain.usecase.direction.GetDirectionUseCase
import com.eight_potato.domain.usecase.reststop.GetRestStopsUseCase
import com.eight_potato.rest.model.RestStopUiModel
import com.eight_potato.rest.model.toUiModel
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
    private val getDirectionUseCase: GetDirectionUseCase,
    private val getRestStopsUseCase: GetRestStopsUseCase
) : ViewModel() {
    private val _path = MutableStateFlow<List<PoiUiModel>>(emptyList())
    val path: StateFlow<List<PoiUiModel>> = _path.asStateFlow()

    private val _restStops = MutableStateFlow<List<RestStopUiModel>>(emptyList())
    val restStops: StateFlow<List<RestStopUiModel>> = _restStops.asStateFlow()

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
                getRestStops(startPoi, endPoi, it.highways)
            }.onFailure {
                println(it)
            }
        }
    }

    private fun getRestStops(
        from: PoiUiModel,
        to: PoiUiModel,
        highways: List<HighwayModel>
    ) {
        viewModelScope.launch {
            getRestStopsUseCase(
                from = from.toModel(),
                to = to.toModel(),
                highways = highways
            ).onSuccess {
                _restStops.value = it.map { restStop -> restStop.toUiModel() }
            }.onFailure {
                println(it)
            }
        }
    }
}