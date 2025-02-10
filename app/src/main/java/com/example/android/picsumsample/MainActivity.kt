package com.example.android.picsumsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.android.picsumsample.feature.photodetail.PhotoDetailView
import com.example.android.picsumsample.feature.photolist.PhotoListView
import com.example.android.picsumsample.ui.theme.PicsumSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            PicsumSampleTheme() {
                NavHost(navController = navController, startDestination = PhotoListDest) {
                    composable<PhotoListDest> {
                        PhotoListView(onPhotoSelected = { photo ->
                            navController.navigate(photo    .photoDetailDest())
                        })
                    }
                    composable<PhotoDetailDest> { backStackEntry ->
                        val details = backStackEntry.toRoute<PhotoDetailDest>().toPhotoDetail()
                        PhotoDetailView(details = details) { navController.navigateUp() }
                    }
                }
            }
        }
    }
}
