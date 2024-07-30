package com.eight_potato.network.model.response.tmap

import com.google.gson.annotations.SerializedName

/**
 * Tmap의 Reverse GeoCoding 결과
 */
data class TmapGeoAddressResponse(
    @SerializedName("addressInfo")
    val addressInfo: AddressInfo
) {
    data class AddressInfo(
        @SerializedName("fullAddress")
        val fullAddress: String, // 주소
        @SerializedName("buildingName")
        val buildingName: String // 주소 건물명
    )
}
