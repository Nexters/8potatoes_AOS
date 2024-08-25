package com.eight_potato.data.datasource.reststop

import com.eight_potato.data.model.address.PoiData
import com.eight_potato.data.model.direction.HighwayData
import com.eight_potato.data.model.reststop.DetailRestStopData
import com.eight_potato.data.model.reststop.RestStopData

interface RestStopDatasource {
    /**
     * 휴게소 리스트 요청
     * @param from 출발지 위치
     * @param to 도착지 위치
     * @param highways 고속도로와 그 위치 정보
     */
    suspend fun getRestStops(
        from: PoiData,
        to: PoiData,
        highways: List<HighwayData>
    ): Result<List<RestStopData>>

    /**
     * 특정 휴게소 상세 정보 요청
     * @param restStopCode 휴게소 코드
     */
    suspend fun getDetailRestStop(
        restStopCode: String
    ): Result<DetailRestStopData>
}