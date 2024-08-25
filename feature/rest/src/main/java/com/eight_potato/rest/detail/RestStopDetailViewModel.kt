package com.eight_potato.rest.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eight_potato.domain.usecase.reststop.GetDetailRestStopUseCase
import com.eight_potato.rest.model.DetailRestStopUiModel
import com.eight_potato.rest.model.toUiModel
import com.eight_potato.ui.model.menu.MenuType
import com.eight_potato.ui.model.menu.MenuUiModel
import com.eight_potato.ui.model.menu.RecommendMenuUIModel
import com.eight_potato.ui.model.menu.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestStopDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    // 휴게소 상세 정보 요청
    private val getDetailRestStopUseCase: GetDetailRestStopUseCase
): ViewModel() {
    // 휴게소 정보
    val restStopCode: String? = savedStateHandle[RestStopDetailActivity.REST_STOP_ARGS]

    // 휴게소 정보
    private val _restStop = MutableStateFlow<DetailRestStopUiModel?>(null)
    val restStop: StateFlow<DetailRestStopUiModel?> = _restStop.asStateFlow()

    // 현재 상단
    private val _currentTab = MutableStateFlow(RestStopDetailActivity.Companion.RestStopTab.MENU)
    val currentTab: StateFlow<RestStopDetailActivity.Companion.RestStopTab> = _currentTab.asStateFlow()

    // 현재 메뉴 탭
    private val _menuTypes = MutableStateFlow<List<MenuType>>(emptyList())
    val menuTypes: StateFlow<List<MenuType>> = _menuTypes.asStateFlow()

    private val _groupedMenus = MutableStateFlow<Map<MenuType, List<MenuUiModel>>>(emptyMap())
    val groupedMenus: StateFlow<Map<MenuType, List<MenuUiModel>>> = _groupedMenus.asStateFlow()

    // 추천 메뉴
    private val _recommendedMenus = MutableStateFlow<List<RecommendMenuUIModel>>(emptyList())
    val recommendedMenus: StateFlow<List<RecommendMenuUIModel>> = _recommendedMenus.asStateFlow()

    private val _currentMenuType = MutableStateFlow<MenuType>(MenuType.NONE)
    val currentMenuType: StateFlow<MenuType> = _currentMenuType.asStateFlow()

    // 탭이 변경되어 스크롤 되는 경우
    var isChangedByTabSelect = false

    init {
        getDetailRestStop()
    }

    /**
     * 휴게소 상세 정보 요청
     */
    private fun getDetailRestStop() {
        restStopCode ?: return
        viewModelScope.launch {
            getDetailRestStopUseCase(restStopCode)
                .onSuccess {
                    _restStop.value = it.toUiModel()

                    val menus = hashMapOf<MenuType, List<MenuUiModel>>()
                    if (it.menus.recommendedMenus.isNotEmpty()) {
                        menus[MenuType.RECOMMEND] = it.menus.recommendedMenus.map { menu ->
                            menu.toUiModel()
                        }
                    }
                    menus.putAll(
                        it.menus.normalMenus.map { menu -> menu.toUiModel() }
                            .groupBy { it.menuType }
                    )
                    _menuTypes.value = menus.keys.sortedBy { type -> type.ordinal }
                    _groupedMenus.value = menuTypes.value.associateWith { type -> (menus[type] ?: emptyList()) }
                    _currentMenuType.value = menuTypes.value.first()
                    _recommendedMenus.value = it.menus.representativeMenus.map { menu -> menu.toUiModel() }
                }.onFailure {
                    println(it)
                }
        }
    }

    // 탭 변경
    fun changeTab(
        tab: RestStopDetailActivity.Companion.RestStopTab
    ) {
        _currentTab.value = tab
    }

    // 메뉴 타입 변경
    fun changeMenuType(
        tab: MenuType,
        isChangedByTab: Boolean
    ) {
        isChangedByTabSelect = isChangedByTab
        _currentMenuType.value = tab
    }
}