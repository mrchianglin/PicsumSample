package com.example.android.picsumsample.data.models

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data Transfer Object for Photo information fetched from remote source.
 * This class is responsible for serializing the API response to Kotlin data structures.
 *
 * @property format Format of photo
 * @property width Width of photo
 * @property height height of photo
 * @property filename Name of photo's file
 * @property id Unique identifier for the photo
 * @property author Name of photo's author
 * @property authorUrl Url to author's page
 * @property postUrl Url to photo
 */
@Serializable
data class PhotoListItemRemoteDto(
    @Required @SerialName("format") val format: PhotoFormat,
    @Required @SerialName("width") val width: Int,
    @Required @SerialName("height") val height: Int,
    @Required @SerialName("filename") val filename: String,
    @Required @SerialName("id") val id: Int,
    @Required @SerialName("author") val author: String,
    @Required @SerialName("author_url") val authorUrl: String,
    @Required @SerialName("post_url") val postUrl: String
) {
    fun toDomainModel() = PhotoListItem(
        id = id,
        filename = filename,
        authorName = author,
        width = width,
        height = height
    )
}
