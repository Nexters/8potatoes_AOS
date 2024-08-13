package com.eight_potato.ui.model.address

import com.eight_potato.domain.model.address.AddressModel
import java.io.Serializable

/**
 * 장소의 위치 정보 Ui Model
 */
data class AddressUiModel(
    val id: String? = null, // 관심 장소 id
    val name: String, // 장소 이름
    val roadAddr: String, // 도로명 주소
    val oldAddr: String, // 지번 주소
    val poi: PoiUiModel? // 위치 좌표 정보
) : Serializable

fun AddressModel.toUiModel(): AddressUiModel {
    return AddressUiModel(
        id = id,
        name = name,
        roadAddr = fullAddress ?: "",
        oldAddr = oldAddress ?: "",
        poi = poi?.toUiModel()
    )
}