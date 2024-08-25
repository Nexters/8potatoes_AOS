package com.eight_potato.domain.model.reststop

import com.eight_potato.domain.model.address.PoiModel

data class RestStopModel(
    val name: String,
    val direction: String?,
    val code: String,
    val isOperating: Boolean,
    val gasolinePrice: String?,
    val dieselPrice: String?,
    val lpgPrice: String?,
    val naverRating: Float?,
    val foodMenusCount: Int,
    val location: PoiModel,
    val isRecommend: Boolean
)
