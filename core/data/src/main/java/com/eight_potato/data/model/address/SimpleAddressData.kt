package com.eight_potato.data.model.address

import com.eight_potato.domain.model.address.SimpleAddressModel
import com.eight_potato.network.model.response.tmap.TmapGeoAddressResponse

/**
 * 간단한 내용만 포함하고 있는 주소 정보
 */
data class SimpleAddressData(
    val fullAddress: String, // 주소
    val buildingName: String // 건물명
)

fun TmapGeoAddressResponse.toData(): SimpleAddressData {
    return SimpleAddressData(
        fullAddress = addressInfo.fullAddress.split(",").getOrNull(2) ?: "",
        buildingName = addressInfo.buildingName
    )
}

fun SimpleAddressData.toModel(): SimpleAddressModel {
    return SimpleAddressModel(
        fullAddress = fullAddress,
        buildingName = buildingName
    )
}