package com.eight_potato.ui.model.address

import java.io.Serializable

/**
 * 위치 좌표 정보
 */
data class PoiUiModel(
    val lat: Float?, // 위도
    val lon: Float? // 경도
): Serializable

val TEST_POI = PoiUiModel(0f, 0f)