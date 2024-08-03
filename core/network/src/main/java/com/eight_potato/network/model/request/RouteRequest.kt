package com.eight_potato.network.model.request

import com.google.gson.annotations.SerializedName

/**
 * 경로 검색 요청 Body
 */
data class RouteRequest(
    @SerializedName("roadType")
    val roadType: Int, // 출발 지점의 도로 타입
    @SerializedName("startX")
    val startX: Float, // 출발지 경도
    @SerializedName("startY")
    val startY: Float, // 출발지 위도
    @SerializedName("endX")
    val endX: Float, // 도착지 경도
    @SerializedName("endY")
    val endY: Float, // 도착지 위도
    @SerializedName("searchOption")
    val searchOption: Int, // 경로 탐색 옵션
    @SerializedName("mainRoadInfo")
    val mainRoadInfo: String = "Y" // 경로상 주요 도로 정보 표출 여부
)
