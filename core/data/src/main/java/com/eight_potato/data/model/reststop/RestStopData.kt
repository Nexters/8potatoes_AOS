package com.eight_potato.data.model.reststop

import com.eight_potato.data.model.address.PoiData
import com.eight_potato.data.model.address.toModel
import com.eight_potato.domain.model.reststop.RestStopModel
import com.eight_potato.network.model.response.reststop.RestStopResponse

data class RestStopData(
    val name: String,
    val direction: String?,
    val code: String,
    val isOperating: Boolean,
    val gasolinePrice: String?,
    val dieselPrice: String?,
    val lpgPrice: String?,
    val naverRating: Float?,
    val foodMenusCount: Int,
    val location: PoiData,
    val isRecommend: Boolean
)

fun RestStopResponse.toData(): RestStopData {
    return RestStopData(
        name = name,
        direction = direction,
        code = code,
        isOperating = isOperating,
        gasolinePrice = gasolinePrice,
        dieselPrice = dieselPrice,
        lpgPrice = lpgPrice,
        naverRating = naverRating,
        foodMenusCount = foodMenusCount,
        location = PoiData(
            lat = location.latitude,
            lon = location.longitude
        ),
        isRecommend = isRecommend
    )
}

fun RestStopData.toModel(): RestStopModel {
    return RestStopModel(
        name = name,
        direction = direction,
        code = code,
        isOperating = isOperating,
        gasolinePrice = gasolinePrice,
        dieselPrice = dieselPrice,
        lpgPrice = lpgPrice,
        naverRating = naverRating,
        foodMenusCount = foodMenusCount,
        location = location.toModel(),
        isRecommend = isRecommend
    )
}
