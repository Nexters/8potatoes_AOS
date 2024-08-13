package com.eight_potato.data.model.address

import com.eight_potato.domain.model.address.SimpleAddressModel
import com.eight_potato.network.model.response.tmap.TmapGeoAddressResponse

/**
 * 간단한 내용만 포함하고 있는 주소 정보
 */
data class SimpleAddressData(
    val fullAddress: String, // 도로명
    val oldAddress: String, // 지번
    val buildingName: String // 건물명
)

fun TmapGeoAddressResponse.toData(): SimpleAddressData {
    val addresses =  addressInfo.fullAddress.split(",")
    return SimpleAddressData(
        fullAddress = addresses.getOrNull(2) ?: "",
        oldAddress = addresses.getOrNull(1) ?: "",
        buildingName = addressInfo.buildingName
    )
}

fun SimpleAddressData.toModel(): SimpleAddressModel {
    return SimpleAddressModel(
        fullAddress = fullAddress,
        oldAddress = oldAddress,
        buildingName = buildingName
    )
}