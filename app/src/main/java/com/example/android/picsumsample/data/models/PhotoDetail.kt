package com.example.android.picsumsample.data.models

import android.net.Uri
import com.example.android.picsumsample.feature.photodetail.HTTPS_SCHEME
import com.example.android.picsumsample.feature.photodetail.IMAGE_QUERY_PARAM_KEY
import com.example.android.picsumsample.feature.photodetail.PICSUM_AUTHORITY

/**
 * Represents a photo's details used by Photo Details
 *
 * @property id Unique identifier for the photo
 * @property width Width of photo
 * @property height height of photo
 * @property authorName Name of photo's author
 */
data class PhotoDetail(
    val id: Int,
    val width: Int,
    val authorName: String,
    val height: Int
) {

    /**
     * Returns url endpoint string following this format:
     * https://picsum.photos/{width}/{height}?image={id}
     *
     * @return Url as string
     */
    fun photoEndpoint() = Uri.Builder()
        .scheme(HTTPS_SCHEME)
        .authority(PICSUM_AUTHORITY)
        .appendPath(width.toString())
        .appendPath(height.toString())
        .appendQueryParameter(IMAGE_QUERY_PARAM_KEY, id.toString())
        .build()
        .toString()
}