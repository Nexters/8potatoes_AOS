package com.eight_potato.data.di

import com.eight_potato.data.datasource.address.AddressDatasource
import com.eight_potato.data.datasource.address.DefaultAddressDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DatasourceModule {
    @Binds
    @Singleton
    fun bindTmapDatasource(
        tmapDatasource: DefaultAddressDatasource
    ): AddressDatasource
}