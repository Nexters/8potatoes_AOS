package com.eight_potato.search.model

import com.eight_potato.domain.model.address.SimpleAddressModel

data class SimpleAddressUiModel(
    val fullAddress: String,
    val buildingName: String
)

fun SimpleAddressModel.toUiModel(): SimpleAddressUiModel {
    return SimpleAddressUiModel(
        fullAddress = fullAddress,
        buildingName = buildingName
    )
}
