package com.eight_potato.network.model.response.route

import com.google.gson.annotations.SerializedName

/**
 * 경로 검색 결과 Response
 */
data class RouteResponse(
    @SerializedName("features")
    val features: List<Feature>
)

data class Feature(
    @SerializedName("type")
    val type: String,
    @SerializedName("geometry")
    val geometry: Geometry,
    @SerializedName("properties")
    val property: Property
)

data class Geometry(
    @SerializedName("type")
    val type: String,
    @SerializedName("coordinates")
    val coordinates: List<*>
)

data class Property(
    @SerializedName("name")
    val name: String,
    @SerializedName("roadType")
    val roadType: Int,
    @SerializedName("departIdx")
    val departIdx: Int?,
    @SerializedName("destIdx")
    val destIdx: Int?
)
