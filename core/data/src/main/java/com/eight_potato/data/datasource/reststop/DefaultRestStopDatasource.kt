package com.eight_potato.data.datasource.reststop

import com.eight_potato.data.model.address.PoiData
import com.eight_potato.data.model.direction.HighwayData
import com.eight_potato.data.model.reststop.DetailRestStopData
import com.eight_potato.data.model.reststop.RestStopData
import com.eight_potato.data.model.reststop.toData
import com.eight_potato.data.util.ApiCallUtil
import com.eight_potato.network.api.HyusikApi
import com.eight_potato.network.model.request.RestStopRequest
import javax.inject.Inject
import kotlin.math.min

class DefaultRestStopDatasource @Inject constructor(
    private val restStopApi: HyusikApi
): RestStopDatasource{
    // 휴게소 리스트 요청
    override suspend fun getRestStops(
        from: PoiData,
        to: PoiData,
        highways: List<HighwayData>
    ): Result<List<RestStopData>> {
        return kotlin.runCatching {
            ApiCallUtil {
                restStopApi.getRestStops(
                    from = "${from.lat},${from.lon}",
                    to = "${to.lat},${to.lon}",
                    body = RestStopRequest(
                        highways = highways.map {
                            it.name to it.pois.map { pois ->
                                pois.map { poi ->
                                    listOf(poi.lon, poi.lat)
                                }
                            }
                        }.toMap()
                    )
                )
            }.first().restStops.map { it.toData() }
        }
    }

    // 휴게소 상세 정보 요청
    override suspend fun getDetailRestStop(
        restStopCode: String
    ): Result<DetailRestStopData> {
        return runCatching {
            ApiCallUtil {
                restStopApi.getDetailRest(
                    restStopCode = restStopCode
                )
            }.toData()
        }
    }
}