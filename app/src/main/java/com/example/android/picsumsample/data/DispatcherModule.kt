package com.example.android.picsumsample.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Dagger module for providing DispatcherProvider.
 */
@Module
@InstallIn(SingletonComponent::class)
object DispatcherProviderModule {

    /**
     * Provides DispatcherProvider instance
     */
    @Singleton
    @Provides
    fun providesDispatcherProvider(): DispatcherProvider = DispatcherProviderImpl()
}

/**
 * Provides coroutine dispatchers for different threading contexts.
 * This allows for easier management of background tasks and UI updates.
 */
interface DispatcherProvider {
    val defaultDispatcher: CoroutineDispatcher
    val mainDispatcher: CoroutineDispatcher
    val ioDispatcher: CoroutineDispatcher
}

/**
 * Dispatcher Provider to be used in production code.
 */
class DispatcherProviderImpl @Inject constructor() : DispatcherProvider {
    override val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
    override val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
    override val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
}