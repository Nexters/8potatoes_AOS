package com.eight_potato.data.datasource.direction

import com.eight_potato.data.model.address.PoiData
import com.eight_potato.data.model.direction.DirectionData
import com.eight_potato.data.model.direction.toData
import com.eight_potato.data.util.ApiCallUtil
import com.eight_potato.network.api.NaverApi
import javax.inject.Inject

/**
 * 경로와 관련된 Datasource
 */
class DefaultDirectionDatasource @Inject constructor(
    private val naverApi: NaverApi
) : DirectionDatasource {
    override suspend fun getDirection(
        start: PoiData,
        end: PoiData
    ): Result<DirectionData> {
        return runCatching {
            ApiCallUtil {
                naverApi.getDirection(
                    start = start.toString(),
                    end = end.toString()
                )
            }.toData()
        }
    }
}