package com.eight_potato.data.model.direction

import com.eight_potato.data.model.address.PoiData
import com.eight_potato.data.model.address.toModel
import com.eight_potato.domain.model.direction.DirectionModel
import com.eight_potato.network.model.response.route.RouteResponse

/**
 * 경로 Data
 */
data class DirectionData(
    val path: List<PoiData>, // 경로를 구성하는 좌표열
    val highways: List<String> // 고속도로
)

fun RouteResponse.toData(): DirectionData {
    val highways = mutableListOf<String>()
    val routes = features.flatMap {
        val property = it.property
        val coordinates = it.geometry.coordinates
        if (property.roadType == 0) {
            highways.add(property.name)
        }

        val firstItem = coordinates.first()
        if (firstItem is Double) {
            val secondItem = coordinates[1] as Double
            listOf(PoiData(secondItem.toFloat(), firstItem.toFloat()))
        } else coordinates.mapNotNull {
            (it as? List<Double>)?.let { poi ->
                val firstItem = poi.first()
                val secondItem = poi[1] as Double
                PoiData(secondItem.toFloat(), firstItem.toFloat())
            }
        }
    }

    return DirectionData(
        path = routes,
        highways = highways
    )
}

fun DirectionData.toModel(): DirectionModel {
    return DirectionModel(path.map { it.toModel() })
}