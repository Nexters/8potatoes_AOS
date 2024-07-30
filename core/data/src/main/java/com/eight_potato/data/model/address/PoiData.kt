package com.eight_potato.data.model.address

import com.eight_potato.domain.model.address.PoiModel

data class PoiData(
    val lat: Float, // 위도
    val lon: Float // 경도
) {
    override fun toString(): String {
        return "$lat,$lon"
    }
}

fun PoiData.toModel(): PoiModel {
    return PoiModel(lat, lon)
}