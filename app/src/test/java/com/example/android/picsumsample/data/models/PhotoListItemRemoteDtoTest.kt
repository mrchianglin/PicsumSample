package com.example.android.picsumsample.data.models

import org.junit.Assert.assertEquals
import org.junit.Test

class PhotoListItemRemoteDtoTest {

    @Test
    fun remoteDtoToDomainModel() {
        val remoteDto = PhotoListItemRemoteDto(
            format = PhotoFormat.JPEG,
            width = 800,
            height = 600,
            filename = "0.jpeg",
            id = 0,
            author = "chester",
            authorUrl = "chesterUrl",
            postUrl = "postUrl"
        )

        assertEquals(
            PhotoListItem(
                id = 0,
                width = 800,
                height = 600,
                filename = "0.jpeg",
                authorName = "chester"
            ),
            remoteDto.toDomainModel()
        )
    }
}