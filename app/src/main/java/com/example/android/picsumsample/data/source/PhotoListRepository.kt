package com.example.android.picsumsample.data.source

import com.example.android.picsumsample.data.PicsumResult
import com.example.android.picsumsample.data.models.PhotoListItem
import kotlinx.coroutines.flow.Flow

interface PhotoListRepository {

    suspend fun getPhotoList(): Flow<PicsumResult<List<PhotoListItem>>>
}