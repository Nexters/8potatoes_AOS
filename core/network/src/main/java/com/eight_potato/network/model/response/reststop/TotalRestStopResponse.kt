package com.eight_potato.network.model.response.reststop

import com.google.gson.annotations.SerializedName
import javax.inject.Singleton

/**
 * 휴게소 리스트 요청 Response
 */
data class TotalRestStopResponse(
    @SerializedName("totalReststopCount")
    val totalRestStopCount: Int,
    @SerializedName("reststops")
    val restStops: List<RestStopResponse>
)

/**
 * 실제 휴게소 정보
 */
data class RestStopResponse(
    @SerializedName("name")
    val name: String, // 휴게소 이름
    @SerializedName("direction")
    val direction: String?, // 휴게소 방향
    @SerializedName("code")
    val code: String, // 휴게소 코드
    @SerializedName("isOperating")
    val isOperating: Boolean, // 운영중 여부
    @SerializedName("gasolinePrice")
    val gasolinePrice: String?, // 휘발유 가격
    @SerializedName("dieselPrice")
    val dieselPrice: String?, // 경유 가격
    @SerializedName("lgpPrice")
    val lpgPrice: String?, // LPG 가격
    @SerializedName("naverRating")
    val naverRating: Float?, // 네이버 평점
    @SerializedName("foodMenusCount")
    val foodMenusCount: Int, // 총 메뉴 개수
    @SerializedName("location")
    val location: RestStopPoi, // 휴게소 좌표
    @SerializedName("isRecommend")
    val isRecommend: Boolean // 경로 내 추천 휴게소
) {
    data class RestStopPoi(
        @SerializedName("latitude")
        val latitude: Double, // 위도
        @SerializedName("longitude")
        val longitude: Double // 경도
    )
}


