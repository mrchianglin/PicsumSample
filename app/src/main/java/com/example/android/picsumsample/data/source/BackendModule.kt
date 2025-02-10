package com.example.android.picsumsample.data.source

import com.example.android.picsumsample.data.api.PhotoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

/**
 * Dagger module for providing network-related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object BackendModule {

    private const val BASE_URL = "https://picsum.photos/"
    private const val CONTENT_TYPE = "application/json; charset=UTF8"

    /**
     * Provides the Photo
     */
    @Singleton
    @Provides
    fun providesPhotoApi(retrofit: Retrofit): PhotoApi =
        retrofit.create(PhotoApi::class.java)

    /**
     * Provides the Retrofit instance
     */
    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(CONTENT_TYPE.toMediaType()))
            .build()

    /**
     * Provides HttpLoggingInterceptor that logs request and response lines and
     * their respective headers and bodies if present.
     */
    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    /**
     * Provides OkHttpClient with logging enabled
     */
    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
}