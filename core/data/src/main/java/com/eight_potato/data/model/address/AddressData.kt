package com.eight_potato.data.model.address

import com.eight_potato.domain.model.address.AddressModel
import com.eight_potato.network.model.response.AddressResponse

/**
 * 주소 검색 결과 Response
 */
data class AddressData(
    val totalCount: Int, // 조회한 결과의 총 개수
    val count: Int, // 페이지당 조회 결과 수
    val page: Int, // 조회한 페이지 번호
    val pois: List<Poi> // 주소 검색 결과
) {
    data class Poi(
        val id: String, // 관심 장소 id
        val key: String, // 관심 장소 식별자
        val name: String, // 장소명(시설물 등) 및 업체명
        val lat: Float?, // 위도
        val lon: Float?, // 경도
        val fullAddress: String? // 도로명 주소
    )
}

fun AddressResponse.toData(): AddressData {
    return AddressData(
        totalCount = searchPoiInfo.totalCount,
        count = searchPoiInfo.count,
        page = searchPoiInfo.page,
        pois = searchPoiInfo.pois.poi.map {
            val address = it.address.address.firstOrNull()
            AddressData.Poi(
                id = it.id,
                key = it.key,
                name = it.name,
                lat = address?.lat,
                lon = address?.lon,
                fullAddress = address?.fullAddress
            )
        }
    )
}

fun AddressData.Poi.toModel(): AddressModel {
    return AddressModel(
        id = id,
        key = key,
        name = name,
        lat = lat,
        lon = lon,
        fullAddress = fullAddress
    )
}