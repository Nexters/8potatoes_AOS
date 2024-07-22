package com.eight_potato.domain.usecase.address

import com.eight_potato.domain.model.address.AddressModel
import com.eight_potato.domain.repository.AddressRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 주어진 키워드에 맞는 주소 검색
 */
@Singleton
class GetAddressByKeywordUseCase @Inject constructor(
    private val addressRepository: AddressRepository
) {
    suspend operator fun invoke(
        keyword: String
    ): Result<List<AddressModel>> {
        return addressRepository.getAddress(keyword)
    }
}