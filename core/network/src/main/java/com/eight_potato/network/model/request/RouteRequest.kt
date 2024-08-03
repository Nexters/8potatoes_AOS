package com.eight_potato.network.model.request

import com.google.gson.annotations.SerializedName

/**
 * 경로 검색 요청 Body
 */
data class RouteRequest(
    @SerializedName("startX")
    val startX: Float, // 출발지 경도
    @SerializedName("startY")
    val startY: Float, // 출발지 위도
    @SerializedName("endX")
    val endX: Float, // 도착지 경도
    @SerializedName("endY")
    val endY: Float, // 도착지 위도
    @SerializedName("reqCoordType")
    val reqCoordType: String = "WGS84GEO",
    @SerializedName("resCoordType")
    val resCoordType: String = "WGS84GEO"
)
