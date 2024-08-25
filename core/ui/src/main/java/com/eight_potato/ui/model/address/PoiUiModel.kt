package com.eight_potato.ui.model.address

import com.eight_potato.domain.model.address.PoiModel
import com.naver.maps.geometry.LatLng
import java.io.Serializable

/**
 * 위치 좌표 정보
 */
data class PoiUiModel(
    val lat: Double, // 위도
    val lon: Double // 경도
) : Serializable

fun PoiUiModel.toLatLng() =
    LatLng(lat.toDouble(), lon.toDouble())

fun PoiModel.toUiModel(): PoiUiModel {
    return PoiUiModel(lat, lon)
}

fun PoiUiModel.toModel(): PoiModel {
    return PoiModel(lat, lon)
}