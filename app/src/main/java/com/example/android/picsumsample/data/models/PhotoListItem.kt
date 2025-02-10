package com.example.android.picsumsample.data.models

import com.example.android.picsumsample.PhotoDetailDest

data class PhotoListItem(
    val id: Int,
    val filename: String,
    val authorName: String,
    val width: Int,
    val height: Int
) {
    fun photoDetailDest() = PhotoDetailDest(
        id = id,
        authorName = authorName,
        width = width,
        height = height
    )
}