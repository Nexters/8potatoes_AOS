package com.eight_potato.domain.model.address

data class SimpleAddressModel(
    val fullAddress: String, // 주소
    val oldAddress: String,
    val buildingName: String // 건물명
)
