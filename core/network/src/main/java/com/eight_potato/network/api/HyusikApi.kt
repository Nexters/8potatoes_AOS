package com.eight_potato.network.api

import com.eight_potato.network.model.request.RestStopRequest
import com.eight_potato.network.model.response.reststop.DetailRestStopResponse
import com.eight_potato.network.model.response.reststop.TotalRestStopResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * 휴게소 관련 API
 */
interface HyusikApi {
    /**
     * 휴게소 리스트 요청
     * [POST] /api/highways/reststops
     */
    @POST("api/highways/reststops")
    suspend fun getRestStops(
        @Query("from") from: String,
        @Query("to") to: String,
        @Body body: RestStopRequest
    ): Response<List<TotalRestStopResponse>>

    /**
     * 휴게소 상세 정보 요청
     * [GET] /api/reststop/info
     */
    @GET("api/reststop/info")
    suspend fun getDetailRest(
        @Query("reststopCode") restStopCode: String
    ): Response<DetailRestStopResponse>
}