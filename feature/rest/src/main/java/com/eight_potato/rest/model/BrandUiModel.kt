package com.eight_potato.rest.model

data class BrandUiModel(
    override val id: String,
    override val image: String,
    override val name: String
) : RowItem

val TEST_BRAND = (0..5).map {
    BrandUiModel(
        id = "$it",
        image = "",
        name = "브랜드$it"
    )
}