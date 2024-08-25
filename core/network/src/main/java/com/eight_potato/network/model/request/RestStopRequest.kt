package com.eight_potato.network.model.request

import com.google.gson.annotations.SerializedName

/**
 * 휴게소 리스트 요청 Request
 */
data class RestStopRequest(
    @SerializedName("highways")
    val highways: Map<String, List<List<List<Double>>>>
)
