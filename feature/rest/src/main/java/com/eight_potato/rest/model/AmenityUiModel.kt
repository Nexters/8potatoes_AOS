package com.eight_potato.rest.model

/**
 * 편의 시설 정보
 */
data class AmenityUiModel(
    override val id: String,
    override val image: String,
    override val name: String
): RowItem

val TEST_AMENITY = (0..5).map {
    AmenityUiModel(
        id = "$it",
        image = "",
        name = "편의시설$it"
    )
}