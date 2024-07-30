package com.eight_potato.network.model.response.tmap

import com.google.gson.annotations.SerializedName

/**
 * 주소 검색 결과 Response
 */
data class TmapAddressResponse(
    @SerializedName("searchPoiInfo")
    val searchPoiInfo: SearchPoiInfo
) {
    data class SearchPoiInfo(
        @SerializedName("totalCount")
        val totalCount: Int, // 조회한 결과의 총 개수
        @SerializedName("count")
        val count: Int, // 페이지당 조회 결과 수
        @SerializedName("page")
        val page: Int, // 조회한 페이지 번호
        @SerializedName("pois")
        val pois: Pois
    )

    data class Pois(
        @SerializedName("poi")
        val poi: List<Poi>
    )

    data class Poi(
        @SerializedName("id")
        val id: String, // 관심 장소 id
        @SerializedName("pkey")
        val key: String, // 관심 장소 식별자
        @SerializedName("name")
        val name: String, // 장소명(시설물 등) 및 업체명
        @SerializedName("newAddressList")
        val address: AddressList, // 도로명 주소
    )

    data class AddressList(
        @SerializedName("newAddress")
        val address: List<NewAddress>
    )

    data class NewAddress(
        @SerializedName("centerLat")
        val lat: Float, // 경도
        @SerializedName("centerLon")
        val lon: Float, // 위도
        @SerializedName("fullAddressRoad")
        val fullAddress: String // 도로명 주소
    )
}