package com.eight_potato.rest.model

/**
 * 휴게소 정보
 */
data class RestStopUiModel(
    val id: String,
    val direction: String,
    val name: String,
    val address: String,
    val rate: Float,
    val state : RestStopState,
    val gasolinePrice: Int, // 휘발유 가격
    val dieselPrice: Int, // 경유 가격
    val menuCount: Int
)

val TEST_REST_STOP = (0..1).map {
    RestStopUiModel(
        id = "$it",
        direction = "서욻방면",
        name = "천안 삼거리 휴게소",
        address = "충남 천안시 구성동 171",
        rate = 4.2f,
        state = RestStopState.OPEN,
        gasolinePrice = 1000,
        dieselPrice = 1720,
        menuCount = 32
    )
}
