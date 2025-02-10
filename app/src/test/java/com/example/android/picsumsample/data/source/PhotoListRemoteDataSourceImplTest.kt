package com.example.android.picsumsample.data.source

import com.example.android.picsumsample.data.PicsumResult
import com.example.android.picsumsample.data.api.PhotoApi
import com.example.android.picsumsample.data.models.PhotoFormat
import com.example.android.picsumsample.data.models.PhotoListItemRemoteDto
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PhotoListRemoteDataSourceImplTest {

    @Test
    fun `getPhotoList returns Success with list of photos if api returns success with value`() =
        runTest {
            val api = mockk<PhotoApi> {
                coEvery { getPhotoList() } returns listOf(
                    PhotoListItemRemoteDto(
                        format = PhotoFormat.JPEG,
                        width = 800,
                        height = 600,
                        filename = "0.jpeg",
                        id = 0,
                        author = "chester",
                        authorUrl = "chesterUrl",
                        postUrl = "postUrl"
                    ),
                    PhotoListItemRemoteDto(
                        format = PhotoFormat.JPEG,
                        width = 800,
                        height = 600,
                        filename = "1.jpeg",
                        id = 1,
                        author = "luna",
                        authorUrl = "lunaUrl",
                        postUrl = "postUrl"
                    )
                )
            }
            val dataSource = PhotoListRemoteDataSourceImpl(api)

            val result = dataSource.getPhotoList()

            assertTrue(result is PicsumResult.Success)
            assertEquals(
                listOf(
                    PhotoListItemRemoteDto(
                        format = PhotoFormat.JPEG,
                        width = 800,
                        height = 600,
                        filename = "0.jpeg",
                        id = 0,
                        author = "chester",
                        authorUrl = "chesterUrl",
                        postUrl = "postUrl"
                    ),
                    PhotoListItemRemoteDto(
                        format = PhotoFormat.JPEG,
                        width = 800,
                        height = 600,
                        filename = "1.jpeg",
                        id = 1,
                        author = "luna",
                        authorUrl = "lunaUrl",
                        postUrl = "postUrl"
                    )
                ),
                (result as PicsumResult.Success).invoke()
            )
        }

    @Test
    fun `getPhotoList returns Success with empty list of photos if api returns success with empty list`() =
        runTest {
            val api = mockk<PhotoApi> {
                coEvery { getPhotoList() } returns emptyList()
            }
            val dataSource = PhotoListRemoteDataSourceImpl(api)

            val result = dataSource.getPhotoList()

            assertTrue(result is PicsumResult.Success)
            assertEquals(
                emptyList<PhotoListItemRemoteDto>(),
                (result as PicsumResult.Success).invoke()
            )
        }

    @Test
    fun `getPhotoList returns Fail if api returns malformed data`() =
        runTest {
            val api = mockk<PhotoApi> {
                coEvery { getPhotoList() } throws Exception()
            }
            val dataSource = PhotoListRemoteDataSourceImpl(api)

            val result = dataSource.getPhotoList()

            assertTrue(result is PicsumResult.Fail)
        }
}