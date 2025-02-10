package com.example.android.picsumsample.data.models

import com.example.android.picsumsample.PhotoDetailDest
import org.junit.Assert.assertEquals
import org.junit.Test

class PhotoListItemTest {

    @Test
    fun photoDetailDest() {
        val listItem = PhotoListItem(
            id = 0,
            width = 800,
            height = 600,
            filename = "0.jpeg",
            authorName = "chester"
        )

        assertEquals(
            PhotoDetailDest(
                id = 0,
                authorName = "chester",
                width = 800,
                height = 600
            ),
            listItem.photoDetailDest()
        )
    }
}