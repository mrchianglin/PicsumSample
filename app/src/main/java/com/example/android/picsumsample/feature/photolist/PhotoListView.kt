package com.example.android.picsumsample.feature.photolist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android.picsumsample.R
import com.example.android.picsumsample.data.models.PhotoListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoListView(
    viewModel: PhotoListViewModel = hiltViewModel(),
    onPhotoSelected: (PhotoListItem) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchPhotos()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.photo_list_app_bar_title)) },
                navigationIcon = {}
            )
        }
    ) { innerPadding ->
        when (val uiState = state) {
            is PhotoListUiState.Success -> LazyColumn(
                modifier = Modifier.consumeWindowInsets(innerPadding),
                contentPadding = innerPadding
            ) {
                val photos = uiState.photos
                items(photos) { photo ->
                    ListItem(photo = photo, onClick = { onPhotoSelected(photo) })
                }
            }

            is PhotoListUiState.Loading -> {
                Column(
                    modifier = Modifier
                        .consumeWindowInsets(innerPadding)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }

            is PhotoListUiState.Empty -> {
                Column(
                    modifier = Modifier
                        .consumeWindowInsets(innerPadding)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .consumeWindowInsets(innerPadding)
                            .fillMaxSize(),
                        text = stringResource(R.string.photo_list_empty_msg)
                    )
                }
            }

            is PhotoListUiState.Error -> {
                Column(
                    modifier = Modifier
                        .consumeWindowInsets(innerPadding)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .consumeWindowInsets(innerPadding)
                            .fillMaxSize(),
                        text = stringResource(R.string.photo_list_error_msg)
                    )
                }
            }
        }
    }
}

@Composable
fun ListItem(photo: PhotoListItem, onClick: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(16.dp)) {
        Text(text = photo.filename, style = MaterialTheme.typography.bodySmall)
    }
}