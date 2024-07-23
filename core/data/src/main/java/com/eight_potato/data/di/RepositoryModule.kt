package com.eight_potato.data.di

import com.eight_potato.data.repository.DefaultAddressRepository
import com.eight_potato.domain.repository.AddressRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindAddressRepository(
        addressRepository: DefaultAddressRepository
    ): AddressRepository
}