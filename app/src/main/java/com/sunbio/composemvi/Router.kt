package com.sunbio.composemvi

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sunbio.composemvi.ui.movie.DetailScreen
import com.sunbio.composemvi.ui.movie.MovieScreen

object RouterIntent {
    const val MOVIE_LIST = "moviesScreen"
    const val MOVIE_DETAIL = "movieDetail"
}

@Composable
fun AppNav() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = RouterIntent.MOVIE_LIST) {

        composable(RouterIntent.MOVIE_LIST) {
            MovieScreen(navController)
        }

        composable(RouterIntent.MOVIE_DETAIL) {
            DetailScreen(navController)
        }
    }
}