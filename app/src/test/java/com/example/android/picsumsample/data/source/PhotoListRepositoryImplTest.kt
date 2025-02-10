package com.example.android.picsumsample.data.source

import com.example.android.picsumsample.CoroutinesTestRule
import com.example.android.picsumsample.data.PicsumResult
import com.example.android.picsumsample.data.models.PhotoFormat
import com.example.android.picsumsample.data.models.PhotoListItem
import com.example.android.picsumsample.data.models.PhotoListItemRemoteDto
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class PhotoListRepositoryImplTest {
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `getPhotoList gets initial flow of list of photos from remote data source`() =
        runTest {
            val remoteDataSource = mockk<PhotoListRemoteDataSource> {
                coEvery { getPhotoList() } returns
                        PicsumResult.Success(
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
                            )
                        )
            }
            val repository = PhotoListRepositoryImpl(
                remoteDataSource,
                coroutinesTestRule.dispatcherProvider
            )

            val flow = repository.getPhotoList()

            assertTrue(flow.first() is PicsumResult.Success)
            assertEquals(
                listOf(
                    PhotoListItem(
                        width = 800,
                        height = 600,
                        filename = "0.jpeg",
                        id = 0,
                        authorName = "chester"
                    ),
                    PhotoListItem(
                        width = 800,
                        height = 600,
                        filename = "1.jpeg",
                        id = 1,
                        authorName = "luna"
                    )
                ),
                (flow.first() as PicsumResult.Success).invoke()
            )
        }

    @Test
    fun `getPhotoList gets initial flow of empty list from remote data source`() =
        runTest {
            val remoteDataSource = mockk<PhotoListRemoteDataSource> {
                coEvery { getPhotoList() } returns
                        PicsumResult.Success(emptyList())
            }
            val repository = PhotoListRepositoryImpl(
                remoteDataSource,
                coroutinesTestRule.dispatcherProvider
            )

            val flow = repository.getPhotoList()

            assertTrue(flow.first() is PicsumResult.Success)
            assertEquals(
                emptyList<PhotoListItem>(),
                (flow.first() as PicsumResult.Success).invoke()
            )
        }

    @Test
    fun `getPhotoList gets flow with Fail result if remote data source returns a Fail`() =
        runTest {
            val remoteDataSource = mockk<PhotoListRemoteDataSource> {
                coEvery { getPhotoList() } returns PicsumResult.Fail(Throwable())
            }
            val repository = PhotoListRepositoryImpl(
                remoteDataSource,
                coroutinesTestRule.dispatcherProvider
            )

            val flow = repository.getPhotoList()

            assertTrue(flow.first() is PicsumResult.Fail)
        }
}