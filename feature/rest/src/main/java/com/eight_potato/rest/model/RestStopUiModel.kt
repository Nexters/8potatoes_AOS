package com.eight_potato.rest.model

import com.eight_potato.ui.model.menu.MenuType

/**
 * 휴게소 정보
 */
data class RestStopUiModel(
    val id: String, // 휴게소 id
    val direction: String, // 방면
    val name: String, // 이름
    val address: String, // 주소
    val rate: Float, // 평점
    val state : RestStopState, // 운영 여부
    val startTime: String, // 시작 시간
    val endTime: String, // 종료 시간
    val gasolinePrice: Int, // 휘발유 가격
    val dieselPrice: Int, // 경유 가격
    val menuCount: Int
)

val TEST_REST_STOP = (0..7).map {
    RestStopUiModel(
        id = "$it",
        direction = "서울 방향",
        name = "천안 삼거리 휴게소",
        address = "충남 천안시 구성동 171",
        rate = 4.2f,
        state = RestStopState.OPEN,
        startTime = "09:00",
        endTime = "24:00",
        gasolinePrice = 1000,
        dieselPrice = 1720,
        menuCount = 32
    )
}
