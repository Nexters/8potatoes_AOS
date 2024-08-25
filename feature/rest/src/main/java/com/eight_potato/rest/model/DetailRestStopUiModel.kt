package com.eight_potato.rest.model

import com.eight_potato.domain.model.reststop.DetailRestStopModel
import java.io.Serializable
import java.time.LocalDate

data class DetailRestStopUiModel(
    val direction: String?, // 방면
    val name: String, // 이름
    val address: String, // 주소
    val callNumber: String, // 연락처
    val rate: Float?, // 평점
    val isOperating: Boolean, // 운영 여부
    val startTime: String?, // 시작 시간
    val endTime: String?, // 종료 시간
    val gasolinePrice: String?, // 휘발유 가격
    val dieselPrice: String?, // 경유 가격
    val lpgPrice: String?, // LPG 가격
    val existChargeElectricCar: Boolean, // 전기차 충전 가능 여부
    val existChargeHydrogenCar: Boolean, // 수소차 충전 가능 여부,
    val compatCarParkArea: Int, // 소형차 주차 공간
    val largeCarParkArea: Int, // 대형차 주차 공간
    val disabledPersonParkArea: Int, // 장애인 주차 공간
    val totalPartArea: Int, // 총 주차 공간
    val updateDate: String,
    val menuCount: Int,
    val operatingTime: List<OperatingTimeUiModel>, // 영업시간
    val brands: List<BrandUiModel>, // 브랜드
    val amenities: List<AmenityUiModel> // 편의 시설
) : Serializable {
    val totalParkArea = compatCarParkArea + largeCarParkArea + disabledPersonParkArea
}

fun DetailRestStopModel.toUiModel(): DetailRestStopUiModel {
    return DetailRestStopUiModel(
        direction = direction,
        name = name,
        address = restStop.address,
        callNumber = restStop.phoneNumber,
        rate = naverRating,
        isOperating = isOperating,
        startTime = startTime,
        endTime = endTime,
        gasolinePrice = gasStation.gasolinePrice,
        dieselPrice = gasStation.dieselPrice,
        lpgPrice = gasStation.lpgPrice,
        existChargeElectricCar = gasStation.isElectricChargingStation,
        existChargeHydrogenCar = gasStation.isHydrogenChargingStation,
        compatCarParkArea = parkingData.smallCarSpace,
        largeCarParkArea = parkingData.largeCarSpace,
        disabledPersonParkArea = parkingData.disabledPersonSpace,
        totalPartArea = parkingData.totalSpace,
        updateDate = parkingData.updateDate,
        menuCount = menus.totalMenuCount,
        operatingTime = restStop.restaurantOperatingTimes.map { it.toUiModel() },
        brands = restStop.brands.map { it.toUiModel() },
        amenities = restStop.facilities.map { it.toUiModel() }
    )
}