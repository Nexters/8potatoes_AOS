package com.eight_potato.domain.usecase.direction

import com.eight_potato.domain.model.address.PoiModel
import com.eight_potato.domain.model.direction.DirectionModel
import com.eight_potato.domain.repository.DirectionRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 경로 검색 요청 UseCase
 */
@Singleton
class GetDirectionUseCase @Inject constructor(
    private val directionRepository: DirectionRepository
) {
    /**
     * @param start 출발지 위치 정보
     * @param end 도착지 위치 정보
     */
    suspend operator fun invoke(
        start: PoiModel,
        end: PoiModel
    ): Result<DirectionModel> {
        return directionRepository.getDirection(
            start = start, end = end
        )
    }
}