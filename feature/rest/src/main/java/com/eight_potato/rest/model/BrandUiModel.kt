package com.eight_potato.rest.model

import com.eight_potato.domain.model.reststop.BrandModel

data class BrandUiModel(
    override val name: String,
    override val image: String
) : RowItem

fun BrandModel.toUiModel() = BrandUiModel(brandName, brandLogoUrl)