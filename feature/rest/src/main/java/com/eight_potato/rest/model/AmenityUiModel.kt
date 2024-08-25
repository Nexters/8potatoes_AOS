package com.eight_potato.rest.model

import com.eight_potato.domain.model.reststop.FacilityModel

/**
 * 편의 시설 정보
 */
data class AmenityUiModel(
    override val name: String,
    override val image: String
): RowItem

fun FacilityModel.toUiModel() = AmenityUiModel(name, logoUrl)