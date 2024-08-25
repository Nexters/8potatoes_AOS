package com.eight_potato.domain.model.direction

import com.eight_potato.domain.model.address.PoiModel

data class HighwayModel(
    val name: String,
    val pois: List<List<PoiModel>>
)
