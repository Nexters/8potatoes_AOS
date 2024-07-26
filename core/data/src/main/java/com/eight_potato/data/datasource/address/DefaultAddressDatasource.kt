package com.eight_potato.data.datasource.address

import com.eight_potato.data.model.address.AddressData
import com.eight_potato.data.model.address.SimpleAddressData
import com.eight_potato.data.model.address.toData
import com.eight_potato.data.util.ApiCallUtil
import com.eight_potato.network.api.TmapApi
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject

class DefaultAddressDatasource @Inject constructor(
    private val tmapApi: TmapApi
) : AddressDatasource {
    // 주어진 키워드에 맞는 주소 검색
    override suspend fun getAddress(
        keyword: String
    ): Result<List<AddressData.Poi>> {
        return runCatching {
            ApiCallUtil {
                val encodedKeyword = URLEncoder.encode(
                    keyword, StandardCharsets.UTF_8.toString()
                ).replace("+", "%20")

                tmapApi.getAddress(keyword = encodedKeyword)
            }.toData().pois
        }
    }

    // 좌표에 맞는 주소 검색
    override suspend fun getAddressByPoi(
        lat: Double,
        lon: Double
    ): Result<SimpleAddressData> {
        return runCatching {
            ApiCallUtil {
                tmapApi.getAddressByPoi(
                    lat = lat.toFloat(), lon = lon.toFloat()
                )
            }.toData()
        }
    }
}