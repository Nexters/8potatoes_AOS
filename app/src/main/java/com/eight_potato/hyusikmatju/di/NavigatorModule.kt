package com.eight_potato.hyusikmatju.di

import com.eight_potato.hyusikmatju.navigator.HyusikNavigator
import com.eight_potato.ui.navigator.Navigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigatorModule {
    @Binds
    @Singleton
    fun bindNavigator(
        hyusikNavigator: HyusikNavigator
    ): Navigator
}