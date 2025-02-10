package com.example.android.picsumsample.data.source

import com.example.android.picsumsample.data.PicsumResult
import com.example.android.picsumsample.data.models.PhotoListItemRemoteDto

/**
 * Data source interface for retrieving photo data from remote source.
 */
interface PhotoListRemoteDataSource {

    suspend fun getPhotoList(): PicsumResult<List<PhotoListItemRemoteDto>>
}