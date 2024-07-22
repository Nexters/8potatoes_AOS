package com.eight_potato.data.datasource.address

import com.eight_potato.data.model.address.AddressData

/**
 * 주소와 관련된 Datasource
 */
interface AddressDatasource {
    /**
     * 주어진 키워드에 맞는 주소 검색
     * @param keyword 검색 키워드
     */
    suspend fun getAddress(
        keyword: String
    ): Result<List<AddressData.Poi>>
}