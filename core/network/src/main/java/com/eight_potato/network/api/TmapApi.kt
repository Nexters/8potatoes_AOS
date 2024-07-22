package com.eight_potato.network.api

import com.eight_potato.network.model.response.AddressResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Tmap과 관련된 Api 관리
 */
interface TmapApi {
    /**
     * 주소 검색
     * [GET] https://apis.openapi.sk.com/tmap/pois
     */
    @GET("https://apis.openapi.sk.com/tmap/pois")
    suspend fun getAddress(
        @Header("appKey") appKey: String = "RhcUc25iEc3UR1jX16bUo9AtX7RS3qIT1scW2ahH",
        @Query("searchKeyword") keyword: String
    ): Response<AddressResponse>

}