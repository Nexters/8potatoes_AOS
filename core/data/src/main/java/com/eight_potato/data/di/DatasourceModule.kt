package com.eight_potato.data.di

import com.eight_potato.data.datasource.address.AddressDatasource
import com.eight_potato.data.datasource.address.DefaultAddressDatasource
import com.eight_potato.data.datasource.direction.DefaultDirectionDatasource
import com.eight_potato.data.datasource.direction.DirectionDatasource
import com.eight_potato.data.datasource.reststop.DefaultRestStopDatasource
import com.eight_potato.data.datasource.reststop.RestStopDatasource
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

    @Binds
    @Singleton
    fun bindDirectionDatasource(
        directionDatasource: DefaultDirectionDatasource
    ): DirectionDatasource

    @Binds
    @Singleton
    fun bindRestStopDatasource(
        restStopDatasource: DefaultRestStopDatasource
    ): RestStopDatasource
}