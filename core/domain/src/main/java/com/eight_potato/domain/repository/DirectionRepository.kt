package com.eight_potato.domain.repository

import com.eight_potato.domain.model.address.PoiModel
import com.eight_potato.domain.model.direction.DirectionModel

/**
 * 경로와 관련된 Repository
 */
interface DirectionRepository {
    /**
     * 경로 검색 요청
     * @param start 출발지 위치 정보
     * @param end 도착지 위치 정보
     */
    suspend fun getDirection(
        start: PoiModel,
        end: PoiModel
    ): Result<DirectionModel>
}