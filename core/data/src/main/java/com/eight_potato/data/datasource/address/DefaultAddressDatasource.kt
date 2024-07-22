package com.eight_potato.data.datasource.address

import com.eight_potato.data.model.address.AddressData
import com.eight_potato.data.model.address.toData
import com.eight_potato.data.util.ApiCallUtil
import com.eight_potato.network.api.TmapApi
import javax.inject.Inject

class DefaultAddressDatasource @Inject constructor(
    private val tmapApi: TmapApi
): AddressDatasource{
    // 주어진 키워드에 맞는 주소 검색
    override suspend fun getAddress(
        keyword: String
    ): Result<List<AddressData.Poi>> {
        return runCatching {
            ApiCallUtil {
                tmapApi.getAddress(keyword = keyword)
            }.toData().pois
        }
    }
}