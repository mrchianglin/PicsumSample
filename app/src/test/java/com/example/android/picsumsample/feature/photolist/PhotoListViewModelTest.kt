package com.example.android.picsumsample.feature.photolist

import com.example.android.picsumsample.CoroutinesTestRule
import com.example.android.picsumsample.data.PicsumResult
import com.example.android.picsumsample.data.models.PhotoListItem
import com.example.android.picsumsample.data.source.PhotoListRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PhotoListViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `fetchPhotos success updates UI state to Success`() = runTest {
        val repository = mockk<PhotoListRepository> {
            coEvery { getPhotoList() } returns flowOf(
                PicsumResult.Success(
                    listOf(
                        PhotoListItem(
                            id = 0,
                            width = 800,
                            height = 600,
                            filename = "0.jpeg",
                            authorName = "chester"
                        ),
                        PhotoListItem(
                            id = 1,
                            width = 800,
                            height = 600,
                            filename = "1.jpeg",
                            authorName = "luna"
                        )
                    )
                )
            )
        }
        val viewModel = PhotoListViewModel(repository)

        viewModel.fetchPhotos()

        assertEquals(
            PhotoListUiState.Success(
                listOf(
                    PhotoListItem(
                        id = 0,
                        width = 800,
                        height = 600,
                        filename = "0.jpeg",
                        authorName = "chester"
                    ),
                    PhotoListItem(
                        id = 1,
                        width = 800,
                        height = 600,
                        filename = "1.jpeg",
                        authorName = "luna"
                    )
                )
            ),
            viewModel.uiState.value
        )
    }

    @Test
    fun `fetchPhotos success updates UI state to Empty if repository returns empty list`() = runTest {
        val repository = mockk<PhotoListRepository> {
            coEvery { getPhotoList() } returns flowOf(
                PicsumResult.Success(emptyList<PhotoListItem>())
            )
        }
        val viewModel = PhotoListViewModel(repository)

        viewModel.fetchPhotos()

        assertEquals(
            PhotoListUiState.Empty,
            viewModel.uiState.value
        )
    }

    @Test
    fun `fetchPhotos failure updates UI state to Error`() = runTest {
        val repository = mockk<PhotoListRepository> {
            coEvery { getPhotoList() } returns flowOf(
                PicsumResult.Fail(Exception("error"))
            )
        }
        val viewModel = PhotoListViewModel(repository)

        viewModel.fetchPhotos()

        assertEquals(
            PhotoListUiState.Error("error"),
            viewModel.uiState.value
        )
    }
}
