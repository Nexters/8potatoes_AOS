package com.eight_potato.rest.model

import com.eight_potato.ui.model.menu.MenuType
import java.io.Serializable
import java.time.LocalDate

/**
 * 휴게소 정보
 */
data class RestStopUiModel(
    val id: String, // 휴게소 id
    val direction: String, // 방면
    val name: String, // 이름
    val address: String, // 주소
    val callNumber: String, // 연락처
    val rate: Float, // 평점
    val state: RestStopState, // 운영 여부
    val startTime: String, // 시작 시간
    val endTime: String, // 종료 시간
    val gasolinePrice: Int, // 휘발유 가격
    val dieselPrice: Int, // 경유 가격
    val lpgPrice: Int, // LPG 가격
    val existChargeElectricCar: Boolean, // 전기차 충전 가능 여부
    val existChargeHydrogenCar: Boolean, // 수소차 충전 가능 여부,
    val compatCarParkArea: Int, // 소형차 주차 공간
    val largeCarParkArea: Int, // 대형차 주차 공간
    val disabledPersonParkArea: Int, // 장애인 주차 공간
    val menuCount: Int,
    val updateDate: LocalDate,
    val operatingTime: List<OperatingTimeUiModel>, // 영업시간
    val brands: List<BrandUiModel>, // 브랜드
    val amenities: List<AmenityUiModel> // 편의 시설
) : Serializable {
    val totalParkArea = compatCarParkArea + largeCarParkArea + disabledPersonParkArea
}

val TEST_REST_STOP = (0..7).map {
    RestStopUiModel(
        id = "$it",
        direction = "서울 방향",
        name = "천안 삼거리 휴게소",
        address = "충남 천안시 구성동 171",
        callNumber = "010-1234-1234",
        rate = 4.2f,
        state = RestStopState.OPEN,
        startTime = "09:00",
        endTime = "24:00",
        gasolinePrice = 1000,
        dieselPrice = 1720,
        lpgPrice = 978,
        existChargeElectricCar = true,
        existChargeHydrogenCar = false,
        compatCarParkArea = 236,
        largeCarParkArea = 23,
        disabledPersonParkArea = 12,
        menuCount = 32,
        updateDate = LocalDate.now(),
        operatingTime = TEST_OPERATING,
        brands = TEST_BRAND,
        amenities = TEST_AMENITY
    )
}
