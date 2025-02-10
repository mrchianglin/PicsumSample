package com.example.android.picsumsample.feature.photodetail

import com.example.android.picsumsample.CoroutinesTestRule
import com.example.android.picsumsample.data.models.PhotoDetail
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PhotoDetailViewModelTest {
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `setDetails success updates UI state to Success`() = runTest {
        val viewModel = PhotoDetailViewModel()

        viewModel.setDetails(
            PhotoDetail(
                id = 0,
                width = 800,
                height = 600,
                authorName = "luna"
            )
        )

        assertEquals(
            PhotoDetailUiState.Success(
                details = PhotoDetail(
                    id = 0,
                    width = 800,
                    height = 600,
                    authorName = "luna"
                )
            ),
            viewModel.uiState.value
        )
    }
}
