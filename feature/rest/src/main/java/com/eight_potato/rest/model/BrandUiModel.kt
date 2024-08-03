package com.eight_potato.rest.model

data class BrandUiModel(
    val id: String,
    val image: String,
    val name: String
)

val TEST_BRAND = (0..5).map {
    BrandUiModel(
        id = "$it",
        image = "",
        name = "브랜드$it"
    )
}