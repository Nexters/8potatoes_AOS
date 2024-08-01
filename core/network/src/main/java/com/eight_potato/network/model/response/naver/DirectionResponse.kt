package com.eight_potato.network.model.response.naver

import com.google.gson.annotations.SerializedName

/**
 * 경로 검색 결과 Response
 */
data class DirectionResponse(
    @SerializedName("code")
    val code: Int, // 응답 결과 코드
    @SerializedName("message")
    val message: String, // 응답 결과 문자열
    @SerializedName("route")
    val route: Routes // 응답 결과
) {
    // option code 별 경로 정보를 담는 container
    data class Routes(
        @SerializedName("traoptimal")
        val optimalRoute: Route
    )

    // 특정 option code 의 경로 정보
    data class Route(
        @SerializedName("path")
        val path: List<DoubleArray>, // 경로를 구성 하는 좌표열
        @SerializedName("section")
        val section: Section // 해당 경로를 구성 하는 주요 도로에 관한 정보열
    )

    // 경로를 구성 하는 주요 도로 정보
    data class Section(
        @SerializedName("pointIndex")
        val pointIndex: Int, // 경로를 구성하는 좌표의 인덱스
        @SerializedName("pointCount")
        val pointCount: Int, // 형상점 수
        @SerializedName("distance")
        val distance: Int, // 거리(단위: meter)
        @SerializedName("name")
        val name: String // 도로명
    )
}
