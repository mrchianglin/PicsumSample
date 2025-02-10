package com.example.android.picsumsample

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PicsumApplication: Application(), SingletonImageLoader.Factory {

    override fun newImageLoader(context: PlatformContext): ImageLoader {
        // Coil caches on memory by default so need to configure a custom ImageLoader
        // that utilize disk caching
        return ImageLoader.Builder(context)
            .diskCache(
                DiskCache.Builder()
                    .directory(context.cacheDir.resolve("image_cache"))
                    .maxSizePercent(0.02)
                    .build()
            )
            .build()
    }
}