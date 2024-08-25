package com.eight_potato.ui.model.menu

import com.eight_potato.domain.model.reststop.RepresentativeMenuModel

data class RecommendMenuUIModel(
    val name: String,
    val price: Int,
    val menuImageUrl: String,
    val description: String
)

fun RepresentativeMenuModel.toUiModel() =
    RecommendMenuUIModel(
        name = menuName,
        price = menuPrice,
        menuImageUrl = menuImageUrl,
        description = description
    )
