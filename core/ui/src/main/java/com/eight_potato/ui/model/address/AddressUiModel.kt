package com.eight_potato.ui.model.address

import com.eight_potato.domain.model.address.AddressModel

/**
 * 장소의 위치 정보 Ui Model
 */
data class AddressUiModel(
    val id: String, // 관심 장소 id
    val name: String, // 장소 이름
    val roadAddr: String, // 도로명 주소
    val poi: PoiUiModel // 위치 좌표 정보
)

val TEST_ADDRESS = (0..10).map {
    AddressUiModel(
        id = "$it",
        name = "교보문고 광화문점 $it",
        roadAddr = "서울 종로구 종로 1",
        poi = TEST_POI
    )
}

fun AddressModel.toUiModel(): AddressUiModel {
    return AddressUiModel(
        id = id,
        name = name,
        roadAddr = fullAddress ?: "",
        poi = PoiUiModel(
            lat = lat,
            lon = lon
        )
    )
}