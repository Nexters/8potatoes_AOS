package com.eight_potato.network.di

import androidx.multidex.BuildConfig
import com.eight_potato.network.api.HyusikApi
import com.eight_potato.network.api.TmapApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Converter.Factory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideTmapApi(
        @TmapRetrofit retrofit: Retrofit
    ): TmapApi = retrofit.create(TmapApi::class.java)

    @Provides
    @Singleton
    fun provideHyusikApi(
        @HyusikServer retrofit: Retrofit
    ): HyusikApi = retrofit.create(HyusikApi::class.java)
}

@Qualifier
annotation class TmapRetrofit

@Qualifier
annotation class HyusikServer

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    @TmapRetrofit
    // Tmap API를 위한 Retrofit
    fun provideTmapRetrofit(
        converter: Converter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://apis.openapi.sk.com/tmap/")
            .client(okHttpClient)
            .addConverterFactory(converter)
            .build()
    }

    @Provides
    @Singleton
    @HyusikServer
    fun provideHyusikRetrofit(
        converter: Converter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://175.45.205.16:8082/")
            .client(okHttpClient)
            .addConverterFactory(converter)
            .build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object ConverterFactoryModule {
    @Provides
    @Singleton
    fun provideGsonConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create(
            GsonBuilder().create()
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
object OkHttpClientModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level =  HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
}