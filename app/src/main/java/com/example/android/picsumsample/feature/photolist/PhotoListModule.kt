package com.example.android.picsumsample.feature.photolist

import com.example.android.picsumsample.data.DispatcherProvider
import com.example.android.picsumsample.data.api.PhotoApi
import com.example.android.picsumsample.data.source.PhotoListRemoteDataSource
import com.example.android.picsumsample.data.source.PhotoListRemoteDataSourceImpl
import com.example.android.picsumsample.data.source.PhotoListRepository
import com.example.android.picsumsample.data.source.PhotoListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Dagger Hilt module that provides dependencies for [PhotoListViewModel].
 */
@Module
@InstallIn(ViewModelComponent::class)
object PhotoListModule {

    /**
     * Provides remote data source to fetch photo list from API.
     */
    @Provides
    fun providesPhotoListRemoteDataSource(
        api: PhotoApi
    ): PhotoListRemoteDataSource = PhotoListRemoteDataSourceImpl(api)

    /**
     * Provides [PhotoListRepository] which aggregates data from various sources.
     */
    @Provides
    fun providesPhotoListRepository(
        remoteDataSource: PhotoListRemoteDataSource,
        dispatcherProvider: DispatcherProvider
    ): PhotoListRepository = PhotoListRepositoryImpl(
        remoteDataSource, dispatcherProvider
    )
}
