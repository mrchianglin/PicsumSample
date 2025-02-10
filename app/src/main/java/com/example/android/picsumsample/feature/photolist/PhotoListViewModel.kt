package com.example.android.picsumsample.feature.photolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.picsumsample.data.PicsumResult
import com.example.android.picsumsample.data.models.PhotoListItem
import com.example.android.picsumsample.data.source.PhotoListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(private val repository: PhotoListRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<PhotoListUiState>(PhotoListUiState.Loading)
    val uiState: StateFlow<PhotoListUiState> = _uiState

    fun fetchPhotos() {
        viewModelScope.launch {
            _uiState.value = PhotoListUiState.Loading
            repository.getPhotoList().collect { result ->
                when (result) {
                    is PicsumResult.Success -> {
                        val photos = result()
                        if (photos.isNotEmpty()) {
                            _uiState.value = PhotoListUiState.Success(photos = photos)
                        } else {
                            _uiState.value = PhotoListUiState.Empty
                        }
                    }

                    is PicsumResult.Fail -> {
                        _uiState.value =
                            PhotoListUiState.Error(result.throwable.message.toString())
                    }

                    else -> {}
                }
            }
        }
    }
}

/**
 * Represents UI state for the photo list screen.
 */
sealed class PhotoListUiState {

    /**
     * Indicates that photo list is loading.
     */
    data object Loading : PhotoListUiState()

    /**
     * Indicates successful fetch of photo list.
     * @property photos A list of [PhotoListItem] objects representing the photos.
     */
    data class Success(val photos: List<PhotoListItem>) : PhotoListUiState()

    /**
     * Indicates an error occurred while loading photo list.
     * @property message A string describing the error message.
     */
    data class Error(val message: String) : PhotoListUiState()

    /**
     * Indicates that there are no photos to display (empty state).
     */
    data object Empty : PhotoListUiState()
}
