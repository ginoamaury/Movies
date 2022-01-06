package com.co.ceiba.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import com.co.ceiba.movies.ui.theme.MoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            this.window.statusBarColor = ContextCompat.getColor(this, R.color.black)
            MoviesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    var loadingSplashScreen by remember {
                        mutableStateOf(true)
                    }
                    Navigation(
                        isLoading = { loadingSplashScreen = it },
                        loadingSplashScreen = loadingSplashScreen
                    )
                }
            }
        }
    }
}
