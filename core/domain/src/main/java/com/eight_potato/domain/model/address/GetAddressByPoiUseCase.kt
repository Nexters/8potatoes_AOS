package com.eight_potato.domain.model.address

import com.eight_potato.domain.repository.AddressRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 좌표를 이용해 주소 검색 요청 Use Case
 */
@Singleton
class GetAddressByPoiUseCase @Inject constructor(
    private val addressRepository: AddressRepository
) {
    suspend operator fun invoke(
        lat: Double,
        lon: Double
    ): Result<SimpleAddressModel> {
        return addressRepository.getAddressByPoi(lat, lon)
    }
}