package com.example.android.picsumsample

import com.example.android.picsumsample.data.models.PhotoDetail
import kotlinx.serialization.Serializable

@Serializable object PhotoListDest
@Serializable data class PhotoDetailDest(val id: Int, val authorName: String, val width: Int, val height: Int) {
    fun toPhotoDetail() = PhotoDetail(
        id, width, authorName, height
    )
}

