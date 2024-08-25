package com.eight_potato.domain.model.direction

import com.eight_potato.domain.model.address.PoiModel

/**
 * 경로 Model
 */
data class DirectionModel(
    val path: List<PoiModel>,
    val highways: List<HighwayModel>
)
