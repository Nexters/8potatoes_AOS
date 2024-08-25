package com.eight_potato.domain.repository

import com.eight_potato.domain.model.address.PoiModel
import com.eight_potato.domain.model.direction.HighwayModel
import com.eight_potato.domain.model.reststop.DetailRestStopModel
import com.eight_potato.domain.model.reststop.RestStopModel

interface RestStopRepository {
    /**
     * 휴게소 리스트 요청
     * @param from 출발지 위치
     * @param to 도착지 위치
     * @param highways 고속도로와 그 위치 정보
     */
    suspend fun getRestStops(
        from: PoiModel,
        to: PoiModel,
        highways: List<HighwayModel>
    ): Result<List<RestStopModel>>

    /**
     * 휴게소 상세 정보 요청
     * @param restStopCode 휴게소 코드
     */
    suspend fun getDetailRestStop(
        restStopCode: String
    ): Result<DetailRestStopModel>
}