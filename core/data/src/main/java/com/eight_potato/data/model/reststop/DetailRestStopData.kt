package com.eight_potato.data.model.reststop

import com.eight_potato.domain.model.reststop.AdditionalRestStopModel
import com.eight_potato.domain.model.reststop.BrandModel
import com.eight_potato.domain.model.reststop.DetailRestStopModel
import com.eight_potato.domain.model.reststop.FacilityModel
import com.eight_potato.domain.model.reststop.GasStationModel
import com.eight_potato.domain.model.reststop.MenuModel
import com.eight_potato.domain.model.reststop.ParkModel
import com.eight_potato.domain.model.reststop.RepresentativeMenuModel
import com.eight_potato.domain.model.reststop.RestStopMenuModel
import com.eight_potato.domain.model.reststop.RestaurantModel
import com.eight_potato.network.model.response.reststop.AdditionalRestStopResponse
import com.eight_potato.network.model.response.reststop.BrandResponse
import com.eight_potato.network.model.response.reststop.DetailRestStopResponse
import com.eight_potato.network.model.response.reststop.FacilityResponse
import com.eight_potato.network.model.response.reststop.GasStationResponse
import com.eight_potato.network.model.response.reststop.MenuResponse
import com.eight_potato.network.model.response.reststop.ParkResponse
import com.eight_potato.network.model.response.reststop.RepresentativeMenuResponse
import com.eight_potato.network.model.response.reststop.RestStopMenuResponse
import com.eight_potato.network.model.response.reststop.RestaurantResponse

data class DetailRestStopData(
    val name: String, // 휴게소 이름
    val direction: String?, // 방면
    val isOperating: Boolean, // 식당 운영중 여부
    val startTime: String?, // 식당 운영 시작 시간 (hh:mm)
    val endTime: String?, // 식당 운영 종료 시간 (hh:mm)
    val naverRating: Float?, // 네이버 평점
    val gasStation: GasStationData, // 주유소 정보
    val parkingData: ParkData, // 주차 정보
    val restStop: AdditionalRestStopData, // 휴게소 기타 정보
    val menus: RestStopMenuData // 메뉴 데이터 리스트
)

fun DetailRestStopResponse.toData(): DetailRestStopData {
    return DetailRestStopData(
        name,
        direction,
        isOperating,
        startTime,
        endTime,
        naverRating,
        gasStation.toData(),
        parkingData.toData(),
        restStop.toData(),
        menus.toData()
    )
}

fun DetailRestStopData.toModel(): DetailRestStopModel {
    return DetailRestStopModel(
        name,
        direction,
        isOperating,
        startTime,
        endTime,
        naverRating,
        gasStation.toModel(),
        parkingData.toModel(),
        restStop.toModel(),
        menus.toModel()
    )
}

/**
 * 휴게소 주유소 정보
 */
data class GasStationData(
    val gasolinePrice: String?, // 휘발유 가격
    val dieselPrice: String?, // 경유 가격
    val lpgPrice: String?, // LPG 가격
    val isElectricChargingStation: Boolean, // 전기차 충전소 여부
    val isHydrogenChargingStation: Boolean // 수소차 충전소 여부
)

fun GasStationResponse.toData() =
    GasStationData(
        gasolinePrice, dieselPrice, lpgPrice, isElectricChargingStation, isHydrogenChargingStation
    )

fun GasStationData.toModel() =
    GasStationModel(
        gasolinePrice, dieselPrice, lpgPrice, isElectricChargingStation, isHydrogenChargingStation
    )

/**
 * 휴게소 주차 정보
 */
data class ParkData(
    val smallCarSpace: Int, // 소형차 공간
    val largeCarSpace: Int, // 대형차 공간
    val disabledPersonSpace: Int, // 장애인 주차 공간
    val totalSpace: Int, // 총 주차 공간
    val updateDate: String // 데이터 업데이트 날짜
)

fun ParkResponse.toData() =
    ParkData(smallCarSpace, largeCarSpace, disabledPersonSpace, totalSpace, updateDate)

fun ParkData.toModel() =
    ParkModel(smallCarSpace, largeCarSpace, disabledPersonSpace, totalSpace, updateDate)

/**
 * 휴게소 기타 정보
 */
data class AdditionalRestStopData(
    val restaurantOperatingTimes: List<RestaurantData>, // 식당가 영업시간 정보 리스트
    val brands: List<BrandData>, // 입점 브랜드 리스트
    val facilities: List<FacilityData>, // 편의시설 리스트
    val address: String, // 주소
    val phoneNumber: String // 전화번호
)

fun AdditionalRestStopResponse.toData() =
    AdditionalRestStopData(
        restaurantOperatingTimes.map { it.toData() },
        brands.map { it.toData() },
        facilities.map { it.toData() },
        address,
        phoneNumber
    )

fun AdditionalRestStopData.toModel() =
    AdditionalRestStopModel(
        restaurantOperatingTimes.map { it.toModel() },
        brands.map { it.toModel() },
        facilities.map { it.toModel() },
        address,
        phoneNumber
    )

/**
 * 휴게소 식당 정보
 */
data class RestaurantData(
    val name: String, // 식당 이름
    val operatingTime: String // 식당 영업시간
)

fun RestaurantResponse.toData() =
    RestaurantData(name, operatingTime)

fun RestaurantData.toModel() =
    RestaurantModel(name, operatingTime)


/**
 * 휴게소 입점 브랜드 정보
 */
data class BrandData(
    val brandName: String, // 입점 브랜드 이름
    val brandLogoUrl: String // 브랜드 로그 이미지
)

fun BrandResponse.toData() = BrandData(brandName, brandLogoUrl)
fun BrandData.toModel() = BrandModel(brandName, brandLogoUrl)

/**
 * 휴게소 편의 시설 정보
 */
data class FacilityData(
    val name: String, // 편의시설 이름
    val logoUrl: String // 편의시설 로고 이미지
)

fun FacilityResponse.toData() = FacilityData(name, logoUrl)
fun FacilityData.toModel() = FacilityModel(name, logoUrl)

/**
 * 휴게소 메뉴 정보
 */
data class RestStopMenuData(
    val representativeMenus: List<RepresentativeMenuData>, // 대표 메뉴 데이터 (최대 2개)
    val totalMenuCount: Int, // 전체 메뉴 개수
    val recommendedMenus: List<MenuData>, // 추천 메뉴 데이터
    val normalMenus: List<MenuData> // 일반 메뉴 데이터
)

fun RestStopMenuResponse.toData() = RestStopMenuData(
    representativeMenus.map { it.toData() },
    totalMenuCount,
    recommendedMenus.map { it.toData() },
    normalMenus.map { it.toData() }
)

fun RestStopMenuData.toModel() = RestStopMenuModel(
    representativeMenus.map { it.toModel() },
    totalMenuCount,
    recommendedMenus.map { it.toModel() },
    normalMenus.map { it.toModel() }
)

/**
 * 휴게소 대표 메뉴 정보
 */
data class RepresentativeMenuData(
    val menuName: String, // 대표 메뉴 이름
    val menuPrice: Int, // 대표 메뉴 가격
    val menuImageUrl: String, // 대표 메뉴 이미지
    val description: String // 대표 메뉴 설명
)

fun RepresentativeMenuResponse.toData() =
    RepresentativeMenuData(
        menuName, menuPrice, menuImageUrl, description
    )

fun RepresentativeMenuData.toModel() =
    RepresentativeMenuModel(
        menuName, menuPrice, menuImageUrl, description
    )

/**
 * 휴게소 일반 메뉴 정보
 */
data class MenuData(
    val menuName: String, // 메뉴 이름
    val menuPrice: Int, // 메뉴 가격
    val isSignatureMenu: Boolean, // 시그니처 메뉴 여부
    val isPopularMenu: Boolean, // 인기 메뉴 여부
    val category: String // 메뉴 카테고리
)

fun MenuResponse.toData() = MenuData(
    menuName, menuPrice, isSignatureMenu, isPopularMenu, category
)

fun MenuData.toModel() = MenuModel(
    menuName, menuPrice, isSignatureMenu, isPopularMenu, category
)