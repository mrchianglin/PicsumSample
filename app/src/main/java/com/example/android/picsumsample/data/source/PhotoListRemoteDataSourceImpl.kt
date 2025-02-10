package com.example.android.picsumsample.data.source

import com.example.android.picsumsample.data.PicsumResult
import com.example.android.picsumsample.data.api.PhotoApi
import com.example.android.picsumsample.data.attemptHttp
import com.example.android.picsumsample.data.models.PhotoListItemRemoteDto
import javax.inject.Inject

/**
 * Data source interface for retrieving photo data from remote source.
 */
class PhotoListRemoteDataSourceImpl @Inject constructor(
    private val api: PhotoApi
) : PhotoListRemoteDataSource {

    /**
     * Fetches list of photos from remote data source.
     *
     * @return [PicsumResult] containing a list of [PhotoListItemRemoteDto] or a failure result.
     */
    override suspend fun getPhotoList(): PicsumResult<List<PhotoListItemRemoteDto>> = attemptHttp {
        api.getPhotoList()
    }
}