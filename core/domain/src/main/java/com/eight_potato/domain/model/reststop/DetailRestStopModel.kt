package com.eight_potato.domain.model.reststop

data class DetailRestStopModel(
    val name: String, // 휴게소 이름
    val direction: String?, // 방면
    val isOperating: Boolean, // 식당 운영중 여부
    val startTime: String?, // 식당 운영 시작 시간 (hh:mm)
    val endTime: String?, // 식당 운영 종료 시간 (hh:mm)
    val naverRating: Float?, // 네이버 평점
    val gasStation: GasStationModel, // 주유소 정보
    val parkingData: ParkModel, // 주차 정보
    val restStop: AdditionalRestStopModel, // 휴게소 기타 정보
    val menus: RestStopMenuModel // 메뉴 데이터 리스트
)

/**
 * 휴게소 주유소 정보
 */
data class GasStationModel(
    val gasolinePrice: String?, // 휘발유 가격
    val dieselPrice: String?, // 경유 가격
    val lpgPrice: String?, // LPG 가격
    val isElectricChargingStation: Boolean, // 전기차 충전소 여부
    val isHydrogenChargingStation: Boolean // 수소차 충전소 여부
)

/**
 * 휴게소 주차 정보
 */
data class ParkModel(
    val smallCarSpace: Int, // 소형차 공간
    val largeCarSpace: Int, // 대형차 공간
    val disabledPersonSpace: Int, // 장애인 주차 공간
    val totalSpace: Int, // 총 주차 공간
    val updateDate: String // 데이터 업데이트 날짜
)

/**
 * 휴게소 기타 정보
 */
data class AdditionalRestStopModel(
    val restaurantOperatingTimes: List<RestaurantModel>, // 식당가 영업시간 정보 리스트
    val brands: List<BrandModel>, // 입점 브랜드 리스트
    val facilities: List<FacilityModel>, // 편의시설 리스트
    val address: String, // 주소
    val phoneNumber: String // 전화번호
)

/**
 * 휴게소 식당 정보
 */
data class RestaurantModel(
    val name: String, // 식당 이름
    val operatingTime: String // 식당 영업시간
)

/**
 * 휴게소 입점 브랜드 정보
 */
data class BrandModel(
    val brandName: String, // 입점 브랜드 이름
    val brandLogoUrl: String // 브랜드 로그 이미지
)

/**
 * 휴게소 편의 시설 정보
 */
data class FacilityModel(
    val name: String, // 편의시설 이름
    val logoUrl: String // 편의시설 로고 이미지
)

/**
 * 휴게소 메뉴 정보
 */
data class RestStopMenuModel(
    val representativeMenus: List<RepresentativeMenuModel>, // 대표 메뉴 데이터 (최대 2개)
    val totalMenuCount: Int, // 전체 메뉴 개수
    val recommendedMenus: List<MenuModel>, // 추천 메뉴 데이터
    val normalMenus: List<MenuModel> // 일반 메뉴 데이터
)

/**
 * 휴게소 대표 메뉴 정보
 */
data class RepresentativeMenuModel(
    val menuName: String, // 대표 메뉴 이름
    val menuPrice: Int, // 대표 메뉴 가격
    val menuImageUrl: String, // 대표 메뉴 이미지
    val description: String // 대표 메뉴 설명
)

/**
 * 휴게소 일반 메뉴 정보
 */
data class MenuModel(
    val menuName: String, // 메뉴 이름
    val menuPrice: Int, // 메뉴 가격
    val isSignatureMenu: Boolean, // 시그니처 메뉴 여부
    val isPopularMenu: Boolean, // 인기 메뉴 여부
    val category: String // 메뉴 카테고리
)

