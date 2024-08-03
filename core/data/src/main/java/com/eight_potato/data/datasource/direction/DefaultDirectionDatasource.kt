package com.eight_potato.data.datasource.direction

import com.eight_potato.data.model.address.PoiData
import com.eight_potato.data.model.direction.DirectionData
import com.eight_potato.data.model.direction.toData
import com.eight_potato.data.util.ApiCallUtil
import com.eight_potato.network.api.TmapApi
import com.eight_potato.network.model.request.RouteRequest
import javax.inject.Inject

/**
 * 경로와 관련된 Datasource
 */
class DefaultDirectionDatasource @Inject constructor(
    private val tmapApi: TmapApi
) : DirectionDatasource {
    override suspend fun getDirection(
        start: PoiData,
        end: PoiData
    ): Result<DirectionData> {
        return runCatching {
            ApiCallUtil {
                tmapApi.getRoutes(
                    body = RouteRequest(
                        startX = start.lon,
                        startY = start.lat,
                        endX = end.lon,
                        endY = end.lat
                    )
                )
            }.toData()
        }
    }
}