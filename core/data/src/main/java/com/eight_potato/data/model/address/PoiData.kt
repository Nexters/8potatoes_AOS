package com.eight_potato.data.model.address

import com.eight_potato.domain.model.address.PoiModel

data class PoiData(
    val lat: Double, // 위도
    val lon: Double // 경도
) {
    override fun toString(): String {
        return "$lat,$lon"
    }
}

fun PoiData.toModel(): PoiModel {
    return PoiModel(lat, lon)
}

fun PoiModel.toData(): PoiData {
    return PoiData(lat, lon)
}