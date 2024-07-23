package com.eight_potato.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eight_potato.domain.usecase.address.GetAddressByKeywordUseCase
import com.eight_potato.ui.model.address.AddressUiModel
import com.eight_potato.ui.model.address.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@Suppress("OPT_IN_USAGE")
class SearchAddressViewModel @Inject constructor(
    private val getAddressByKeywordUseCase: GetAddressByKeywordUseCase
) : ViewModel() {
    private val _address = MutableStateFlow<List<AddressUiModel>>(emptyList())
    val address: StateFlow<List<AddressUiModel>> = _address.asStateFlow()

    private val _keyword = MutableStateFlow("")
    val keyword: StateFlow<String> = _keyword.asStateFlow()

    init {
        _keyword.debounce(1_000)
            .onEach { searchAddress(it) }
            .launchIn(viewModelScope)
    }

    fun changeKeyword(keyword: String) {
        _keyword.value = keyword
    }

    private fun searchAddress(keyword: String) {
        viewModelScope.launch {
            if (keyword.isBlank()) {
                _address.value = emptyList()
                return@launch
            }
            getAddressByKeywordUseCase(keyword)
                .onSuccess {
                    _address.value = it.map { address -> address.toUiModel() }
                }
        }
    }
}