package com.eight_potato.data.datasource.direction

import com.eight_potato.data.model.address.PoiData
import com.eight_potato.data.model.direction.DirectionData

/**
 * 경로와 관련된 Datasource
 */
interface DirectionDatasource {
    /**
     * 경로 검색
     * @param start 출발지 위치 정보
     * @param end 도착지 위치 정보
     */
    suspend fun getDirection(
        start: PoiData,
        end: PoiData
    ): Result<DirectionData>
}