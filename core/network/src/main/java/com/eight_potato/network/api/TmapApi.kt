package com.eight_potato.network.api

import com.eight_potato.network.model.request.RouteRequest
import com.eight_potato.network.model.response.route.RouteResponse
import com.eight_potato.network.model.response.tmap.TmapAddressResponse
import com.eight_potato.network.model.response.tmap.TmapGeoAddressResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Tmap과 관련된 Api 관리
 */
interface TmapApi {
    /**
     * 주소 검색
     * [GET] pois
     */
    @GET("pois")
    suspend fun getAddress(
        @Header("appKey") appKey: String = "RhcUc25iEc3UR1jX16bUo9AtX7RS3qIT1scW2ahH",
        @Query("searchKeyword", encoded = true) keyword: String
    ): Response<TmapAddressResponse>

    /**
     * 좌표를 통해 주소 검색
     * [GET] geo/reversegeocoding
     */
    @GET("geo/reversegeocoding")
    suspend fun getAddressByPoi(
        @Header("appKey") appKey: String = "RhcUc25iEc3UR1jX16bUo9AtX7RS3qIT1scW2ahH",
        @Query("addressType") addressType: String = "A10",
        @Query("lat") lat: Float, // 경도
        @Query("lon") lon: Float // 위도
    ): Response<TmapGeoAddressResponse>

    /**
     * 경로 검색
     * [GET] routes
     */
    @GET("routes")
    suspend fun getRoutes(
        @Header("appKey") appKey: String = "RhcUc25iEc3UR1jX16bUo9AtX7RS3qIT1scW2ahH",
        @Query("version") apiVersion: Int = 1,
        @Body body: RouteRequest
    ): Response<RouteResponse>
}