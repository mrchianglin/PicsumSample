package com.example.android.picsumsample.data.api

import com.example.android.picsumsample.data.models.PhotoListItemRemoteDto
import retrofit2.http.GET

/**
 * Interface for interacting with Photo related API endpoints
 */
interface PhotoApi {
    /**
     * Fetches the list of photos from the API.
     *
     * @returns
     */
    @GET("list")
    suspend fun getPhotoList(): List<PhotoListItemRemoteDto>
}