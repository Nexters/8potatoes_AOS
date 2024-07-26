package com.eight_potato.domain.repository

import com.eight_potato.domain.model.address.AddressModel
import com.eight_potato.domain.model.address.SimpleAddressModel

/**
 * 주소와 관련된 Repository
 */
interface AddressRepository {
    /**
     * 주어진 키워드에 맞는 주소 검색
     * @param keyword 검색 키워드
     */
    suspend fun getAddress(
        keyword: String
    ): Result<List<AddressModel>>

    /**
     * 좌표를 통한 주소 검색
     * @param lat 경도
     * @param lon 위도
     */
    suspend fun getAddressByPoi(
        lat: Double,
        lon: Double
    ): Result<SimpleAddressModel>
}