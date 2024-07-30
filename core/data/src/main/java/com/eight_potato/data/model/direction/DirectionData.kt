package com.eight_potato.data.model.direction

import com.eight_potato.domain.model.direction.DirectionModel
import com.eight_potato.network.model.response.naver.DirectionResponse

/**
 * 경로 Data
 */
data class DirectionData(
    val path: List<FloatArray> // 경로를 구성하는 좌표열
)

fun DirectionResponse.toData(): DirectionData {
    return DirectionData(route.optimalRoute.path)
}

fun DirectionData.toModel(): DirectionModel {
    return DirectionModel(path)
}