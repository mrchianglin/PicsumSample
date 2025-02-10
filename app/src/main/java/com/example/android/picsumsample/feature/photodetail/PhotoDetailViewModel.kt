package com.example.android.picsumsample.feature.photodetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.picsumsample.data.models.PhotoDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<PhotoDetailUiState>(PhotoDetailUiState.Loading)
    val uiState: StateFlow<PhotoDetailUiState> = _uiState

    fun setDetails(
        details: PhotoDetail
    ) {
        viewModelScope.launch {
            _uiState.value = PhotoDetailUiState.Loading
            _uiState.value = PhotoDetailUiState.Success(details = details)
        }
    }
}

/**
 * Represents UI state for the photo detail screen.
 */
sealed class PhotoDetailUiState {

    /**
     * Indicates that photo details are loading.
     */
    data object Loading : PhotoDetailUiState()

    /**
     * Indicates successful fetch of photo details.
     * @property details Details of [PhotoDetail] object representing the photo details.
     */
    data class Success(val details: PhotoDetail) : PhotoDetailUiState()

    /**
     * Indicates an error occurred while loading photo list.
     * @property message A string describing the photo message.
     */
    data class Error(val message: String) : PhotoDetailUiState()

    /**
     * Indicates that there are no photos to display (empty state).
     */
    data object Empty : PhotoDetailUiState()
}
