package com.eight_potato.network.api

import com.eight_potato.network.model.response.naver.DirectionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * 네이버 API 관련
 */
interface NaverApi {
    /**
     * 경로 검색
     * [GET] map-direction/v1/driving
     */
    @GET("map-direction/v1/driving")
    suspend fun getDirection(
        @Header("X-NCP-APIGW-API-KEY-ID") clientId: String = "",
        @Header("X-NCP-APIGW-API-KEY") clientSecret: String = "",
        @Query("start") start: String,
        @Query("goal") end: String
    ): Response<DirectionResponse>
}