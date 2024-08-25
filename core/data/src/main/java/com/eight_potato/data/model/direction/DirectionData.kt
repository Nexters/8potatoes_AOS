package com.eight_potato.data.model.direction

import androidx.core.os.persistableBundleOf
import com.eight_potato.data.model.address.PoiData
import com.eight_potato.data.model.address.toModel
import com.eight_potato.domain.model.direction.DirectionModel
import com.eight_potato.domain.model.direction.HighwayModel
import com.eight_potato.network.model.response.route.RouteResponse

/**
 * 경로 Data
 */
data class DirectionData(
    val path: List<PoiData>, // 경로를 구성하는 좌표열
    val highways: List<HighwayData> // 고속도로
)

fun RouteResponse.toData(): DirectionData {
    val highways = hashMapOf<String, MutableList<List<PoiData>>>()
    val routes = features.flatMap {
        val property = it.property
        val coordinates = it.geometry.coordinates

        val firstItem = coordinates.first()
        val pois = if (firstItem is Double) {
            val secondItem = coordinates[1] as Double
            listOf(PoiData(secondItem, firstItem))
        } else coordinates.mapNotNull {
            (it as? List<Double>)?.let { poi ->
                val firstItem = poi.first()
                val secondItem = poi[1] as Double
                PoiData(secondItem, firstItem)
            }
        }

        // lat : 위도, lon : 경도
        if (property.roadType == 0 && it.geometry.type == "LineString") {
            val (minLon, maxLat) = pois.minOf { it.lon } to pois.maxOf { it.lat }
            val (maxLon, minLat) = pois.maxOf { it.lon } to pois.minOf { it.lat }
            val resultPois = listOf(
                PoiData(lon = minLon, lat = maxLat), // 좌측상단
                PoiData(lon = maxLon, lat = maxLat), // 우측상단
                PoiData(lon = minLon, lat = minLat), // 좌측하단
                PoiData(lon = maxLon, lat = minLat) // 우측하단
            )
            if (highways[property.name] != null) {
                highways[property.name]?.add(resultPois)
            } else highways[property.name] = mutableListOf(resultPois)
        }

        pois
    }

    return DirectionData(
        path = routes,
        highways = highways.map {
            HighwayData(
                name = it.key,
                pois = it.value
            )
        }
    )
}

fun DirectionData.toModel(): DirectionModel {
    return DirectionModel(
        path = path.map { it.toModel() },
        highways = highways.map {
            HighwayModel(
                name = it.name,
                pois = it.pois.map { poi ->
                    poi.map { it.toModel() }
                }
            )
        }
    )
}