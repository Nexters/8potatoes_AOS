package com.eight_potato.data.repository

import com.eight_potato.data.datasource.address.AddressDatasource
import com.eight_potato.data.model.address.toModel
import com.eight_potato.domain.model.address.AddressModel
import com.eight_potato.domain.repository.AddressRepository
import javax.inject.Inject

class DefaultAddressRepository @Inject constructor(
    private val addressDatasource: AddressDatasource
) : AddressRepository {
    override suspend fun getAddress(
        keyword: String
    ): Result<List<AddressModel>> {
        return runCatching {
            addressDatasource.getAddress(keyword).getOrThrow().map { it.toModel() }
        }
    }
}