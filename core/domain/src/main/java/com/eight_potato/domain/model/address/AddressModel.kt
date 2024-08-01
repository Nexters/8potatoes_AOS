package com.eight_potato.domain.model.address

data class AddressModel(
    val id: String, // 관심 장소 id
    val key: String, // 관심 장소 식별자
    val name: String, // 장소명(시설물 등) 및 업체명
    val fullAddress: String?, // 도로명 주소
    val poi: PoiModel? // 좌표 정보
)
