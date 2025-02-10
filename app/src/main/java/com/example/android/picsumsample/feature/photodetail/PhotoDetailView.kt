package com.example.android.picsumsample.feature.photodetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.example.android.picsumsample.R
import com.example.android.picsumsample.data.models.PhotoDetail

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoDetailView(
    viewModel: PhotoDetailViewModel = hiltViewModel(),
    details: PhotoDetail,
    onNavIconClick: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.setDetails(
            details = details
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.photo_detail_app_bar_title)) },
                navigationIcon = {
                    IconButton(onClick = onNavIconClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        when (val uiState = state) {
            is PhotoDetailUiState.Success ->
                PhotoDetails(details = uiState.details, padding = innerPadding)

            else -> {}
        }
    }
}

@Composable
fun PhotoDetails(details: PhotoDetail, padding: PaddingValues) {
    val aspectRatio = details.width.toFloat() / details.height.toFloat()

    val painter = rememberAsyncImagePainter(details.photoEndpoint())
    val state by painter.state.collectAsState()

    when (state) {
        is AsyncImagePainter.State.Empty,
        is AsyncImagePainter.State.Loading -> {
            Column(
                modifier = Modifier
                    .consumeWindowInsets(padding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                )
            }
        }

        is AsyncImagePainter.State.Success -> {
            Column(
                modifier = Modifier
                    .consumeWindowInsets(padding)
                    .fillMaxSize(),
                verticalArrangement = if (aspectRatio > 1f) Arrangement.Center else Arrangement.Top
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier.aspectRatio(aspectRatio),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = details.authorName, style = MaterialTheme.typography.bodyMedium)
            }
        }

        is AsyncImagePainter.State.Error -> {
            // Show error UI
        }
    }
}
