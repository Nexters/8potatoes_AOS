package com.eight_potato.data.repository

import com.eight_potato.data.datasource.direction.DirectionDatasource
import com.eight_potato.data.model.address.toData
import com.eight_potato.data.model.direction.toModel
import com.eight_potato.domain.model.address.PoiModel
import com.eight_potato.domain.model.direction.DirectionModel
import com.eight_potato.domain.repository.DirectionRepository
import javax.inject.Inject

/**
 * 경로와 관련된 Repository
 */
class DefaultDirectionRepository @Inject constructor(
    private val directionDatasource: DirectionDatasource
) : DirectionRepository {
    override suspend fun getDirection(
        start: PoiModel,
        end: PoiModel
    ): Result<DirectionModel> {
        return runCatching {
            directionDatasource.getDirection(
                start = start.toData(),
                end = end.toData()
            ).getOrThrow().toModel()
        }
    }
}