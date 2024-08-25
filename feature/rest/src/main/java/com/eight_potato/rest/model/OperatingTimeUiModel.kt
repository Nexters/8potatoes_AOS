package com.eight_potato.rest.model

import com.eight_potato.domain.model.reststop.RestaurantModel

/**
 * 휴게소 영업시간 정보
 * @param name 영업시간 이름
 * @param startTime 시작 시간
 * @param endTime 종료 시간
 */
data class OperatingTimeUiModel(
    val name: String,
    val operatingTime: String
)

fun RestaurantModel.toUiModel() =
    OperatingTimeUiModel(name, operatingTime)