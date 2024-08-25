package com.eight_potato.data.repository

import com.eight_potato.data.datasource.reststop.RestStopDatasource
import com.eight_potato.data.model.address.PoiData
import com.eight_potato.data.model.address.toData
import com.eight_potato.data.model.direction.toData
import com.eight_potato.data.model.reststop.toModel
import com.eight_potato.domain.model.address.PoiModel
import com.eight_potato.domain.model.direction.HighwayModel
import com.eight_potato.domain.model.reststop.DetailRestStopModel
import com.eight_potato.domain.model.reststop.RestStopModel
import com.eight_potato.domain.repository.RestStopRepository
import javax.inject.Inject

class DefaultRestStopRepository @Inject constructor(
    private val restStopDatasource: RestStopDatasource
) : RestStopRepository {
    // 휴게소 리스트 요청
    override suspend fun getRestStops(
        from: PoiModel,
        to: PoiModel,
        highways: List<HighwayModel>
    ): Result<List<RestStopModel>> {
        return kotlin.runCatching {
            restStopDatasource.getRestStops(
                from = from.toData(),
                to = to.toData(),
                highways = highways.map { it.toData() }
            ).getOrThrow().map { it.toModel() }
        }
    }

    // 휴게소 상세 정보 요청
    override suspend fun getDetailRestStop(
        restStopCode: String
    ): Result<DetailRestStopModel> {
        return runCatching {
            restStopDatasource.getDetailRestStop(restStopCode).getOrThrow().toModel()
        }
    }
}