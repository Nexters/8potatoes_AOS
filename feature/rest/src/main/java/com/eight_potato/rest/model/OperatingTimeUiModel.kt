package com.eight_potato.rest.model

/**
 * 휴게소 영업시간 정보
 * @param name 영업시간 이름
 * @param startTime 시작 시간
 * @param endTime 종료 시간
 */
data class OperatingTimeUiModel(
    val name: String,
    val startTime: String,
    val endTime: String
)


val TEST_OPERATING = (0..7).map {
    OperatingTimeUiModel(
        name = "월요일",
        startTime = "09:00",
        endTime = "24:00"
    )
}