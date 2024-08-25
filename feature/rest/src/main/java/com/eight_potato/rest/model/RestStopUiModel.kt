package com.eight_potato.rest.model

import com.eight_potato.domain.model.address.PoiModel
import com.eight_potato.domain.model.reststop.RestStopModel
import com.eight_potato.ui.model.address.PoiUiModel
import com.eight_potato.ui.model.address.toUiModel
import com.eight_potato.ui.model.menu.MenuType
import java.io.Serializable
import java.time.LocalDate

/**
 * 휴게소 정보
 */
data class RestStopUiModel(
    val name: String, // 휴게소 이름
    val direction: String?, // 휴게소 방향
    val code: String, // 휴게소 코드
    val isOperating: Boolean, // 운영중 여부
    val gasolinePrice: String?, // 휘발유 가격
    val dieselPrice: String?, // 경유 가격
    val lpgPrice: String?, // LPG 가격
    val naverRating: Float?, // 네이버 평점
    val foodMenusCount: Int, // 총 메뉴 개수
    val location: PoiUiModel, // 좌표
    val isRecommend: Boolean // 경로 내 추천 휴게소
): Serializable

fun RestStopModel.toUiModel(): RestStopUiModel {
    return RestStopUiModel(
        name = name,
        direction = direction,
        code = code,
        isOperating = isOperating,
        gasolinePrice = gasolinePrice,
        dieselPrice = dieselPrice,
        lpgPrice = lpgPrice,
        naverRating = naverRating,
        foodMenusCount = foodMenusCount,
        location = location.toUiModel(),
        isRecommend = isRecommend
    )
}