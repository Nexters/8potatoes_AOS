package com.eight_potato.ui.model.address

/**
 * 장소의 위치 정보 Ui Model
 */
data class AddressUiModel(
    val name: String, // 장소 이름
    val address: String, // 지번
    val roadAddr: String, // 도로명 주소
    val poi: PoiUiModel // 위치 좌표 정보
)

val TEST_ADDRESS = (0..10).map {
    AddressUiModel(
        name = "교보문고 광화문점 $it",
        address = "서울 종로구 종로 1가",
        roadAddr = "서울 종로구 종로 1",
        poi = TEST_POI
    )
}
