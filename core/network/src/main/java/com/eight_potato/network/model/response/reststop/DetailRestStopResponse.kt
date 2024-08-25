package com.eight_potato.network.model.response.reststop

import com.google.gson.annotations.SerializedName

/**
 * 휴게소 상세 정보 요청 Response
 */
data class DetailRestStopResponse(
    @SerializedName("name")
    val name: String, // 휴게소 이름
    @SerializedName("direction")
    val direction: String?, // 방면
    @SerializedName("isOperating")
    val isOperating: Boolean, // 식당 운영중 여부
    @SerializedName("startTime")
    val startTime: String?, // 식당 운영 시작 시간 (hh:mm)
    @SerializedName("endTime")
    val endTime: String?, // 식당 운영 종료 시간 (hh:mm)
    @SerializedName("naverRating")
    val naverRating: Float?, // 네이버 평점
    @SerializedName("gasStationData")
    val gasStation: GasStationResponse, // 주유소 정보
    @SerializedName("parkingData")
    val parkingData: ParkResponse, // 주차 정보
    @SerializedName("reststopData")
    val restStop: AdditionalRestStopResponse, // 휴게소 기타 정보
    @SerializedName("menuData")
    val menus: RestStopMenuResponse // 메뉴 데이터 리스트
)

/**
 * 휴게소 주유소 정보
 */
data class GasStationResponse(
    @SerializedName("gasolinePrice")
    val gasolinePrice: String?, // 휘발유 가격
    @SerializedName("dieselPrice")
    val dieselPrice: String?, // 경유 가격
    @SerializedName("lpgPrice")
    val lpgPrice: String?, // LPG 가격
    @SerializedName("isElectricChargingStation")
    val isElectricChargingStation: Boolean, // 전기차 충전소 여부
    @SerializedName("isHydrogenChargingStation")
    val isHydrogenChargingStation: Boolean // 수소차 충전소 여부
)

/**
 * 휴게소 주차 정보
 */
data class ParkResponse(
    @SerializedName("smallCarSpace")
    val smallCarSpace: Int, // 소형차 공간
    @SerializedName("largeCarSpace")
    val largeCarSpace: Int, // 대형차 공간
    @SerializedName("disabledPersonSpace")
    val disabledPersonSpace: Int, // 장애인 주차 공간
    @SerializedName("totalSpace")
    val totalSpace: Int, // 총 주차 공간
    @SerializedName("updateDate")
    val updateDate: String // 데이터 업데이트 날짜
)

/**
 * 휴게소 기타 정보
 */
data class AdditionalRestStopResponse(
    @SerializedName("restaurantOperatingTimes")
    val restaurantOperatingTimes: List<RestaurantResponse>, // 식당가 영업시간 정보 리스트
    @SerializedName("brands")
    val brands: List<BrandResponse>, // 입점 브랜드 리스트
    @SerializedName("amenities")
    val facilities: List<FacilityResponse>, // 편의시설 리스트
    @SerializedName("address")
    val address: String, // 주소
    @SerializedName("phoneNumber")
    val phoneNumber: String // 전화번호
)

/**
 * 휴게소 식당 정보
 */
data class RestaurantResponse(
    @SerializedName("restaurantName")
    val name: String, // 식당 이름
    @SerializedName("operatingTime")
    val operatingTime: String // 식당 영업시간
)


/**
 * 휴게소 입점 브랜드 정보
 */
data class BrandResponse(
    @SerializedName("brandName")
    val brandName: String, // 입점 브랜드 이름
    @SerializedName("brandLogoUrl")
    val brandLogoUrl: String // 브랜드 로그 이미지
)

/**
 * 휴게소 편의 시설 정보
 */
data class FacilityResponse(
    @SerializedName("amenityName")
    val name: String, // 편의시설 이름
    @SerializedName("amenityLogoUrl")
    val logoUrl: String // 편의시설 로고 이미지
)

/**
 * 휴게소 메뉴 정보
 */
data class RestStopMenuResponse(
    @SerializedName("representativeMenuData")
    val representativeMenus: List<RepresentativeMenuResponse>, // 대표 메뉴 데이터 (최대 2개)
    @SerializedName("totalMenuCount")
    val totalMenuCount: Int, // 전체 메뉴 개수
    @SerializedName("recommendedMenuData")
    val recommendedMenus: List<MenuResponse>, // 추천 메뉴 데이터
    @SerializedName("normalMenuData")
    val normalMenus: List<MenuResponse> // 일반 메뉴 데이터
)

/**
 * 휴게소 대표 메뉴 정보
 */
data class RepresentativeMenuResponse(
    @SerializedName("representativeMenuName")
    val menuName: String, // 대표 메뉴 이름
    @SerializedName("representativeMenuPrice")
    val menuPrice: Int, // 대표 메뉴 가격
    @SerializedName("representativeMenuImageUrl")
    val menuImageUrl: String, // 대표 메뉴 이미지
    @SerializedName("representativeMenuDescription")
    val description: String // 대표 메뉴 설명
)

/**
 * 휴게소 일반 메뉴 정보
 */
data class MenuResponse(
    @SerializedName("menuName")
    val menuName: String, // 메뉴 이름
    @SerializedName("menuPrice")
    val menuPrice: Int, // 메뉴 가격
    @SerializedName("isSignatureMenu")
    val isSignatureMenu: Boolean, // 시그니처 메뉴 여부
    @SerializedName("isPopularMenu")
    val isPopularMenu: Boolean, // 인기 메뉴 여부
    @SerializedName("menuCategory")
    val category: String // 메뉴 카테고리
)