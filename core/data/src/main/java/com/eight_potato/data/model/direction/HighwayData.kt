package com.eight_potato.data.model.direction

import com.eight_potato.data.model.address.PoiData
import com.eight_potato.data.model.address.toData
import com.eight_potato.domain.model.direction.HighwayModel

/**
 * 고속도로 정보
 */
data class HighwayData(
    val name: String, // 고속도로 이름
    val pois: List<List<PoiData>> // 고속도로 진입점, 탈출 지점 리스트
)

fun HighwayModel.toData(): HighwayData {
    return HighwayData(
        name = name,
        pois = pois.map { poi ->
            poi.map { it.toData() }
        }
    )
}