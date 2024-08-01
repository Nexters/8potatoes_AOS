package com.eight_potato.data.model.direction

import com.eight_potato.data.model.address.PoiData
import com.eight_potato.data.model.address.toModel
import com.eight_potato.domain.model.direction.DirectionModel
import com.eight_potato.network.model.response.naver.DirectionResponse

/**
 * 경로 Data
 */
data class DirectionData(
    val path: List<PoiData> // 경로를 구성하는 좌표열
)

fun DirectionResponse.toData(): DirectionData {
    return DirectionData(
        route.optimalRoute.path.map { (lat, lon) ->
            PoiData(lat, lon)
        }
    )
}

fun DirectionData.toModel(): DirectionModel {
    return DirectionModel(path.map { it.toModel() })
}