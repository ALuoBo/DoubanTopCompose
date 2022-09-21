package com.sunbio.composemvi.ui.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.sunbio.composemvi.api.MovieItem

@Composable
fun MovieScreen(
    viewModel: MovieViewModel = viewModel()
) {

    viewModel.fetchLastedMovies()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        viewModel.uiState.movies?.let {
            items(viewModel.uiState.movies!!) { movie ->
                MovieCard(movie)
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreMovieScreen() {
    MovieScreen()
}

@Composable
fun MovieCard(movie: MovieItem) {
    var backgroundColor by remember {
        mutableStateOf(Color(0xFFBB86FC))
    }
    Card(
        elevation = 6.dp,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = backgroundColor)
        ) {
            AsyncImage(modifier = Modifier
                .height(200.dp)
                .padding(8.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.data[0].poster)
                    .crossfade(true)
                    .allowHardware(false)
                    .build(),
                contentDescription = null,
                onState = {
                    if (it is AsyncImagePainter.State.Success) {
                        backgroundColor = Color(
                            Palette.from(it.result.drawable.toBitmap())
                                .generate()
                                .getMutedColor(0xFFFFFF)
                        )
                    }
                })
            Text(
                modifier = Modifier.padding(8.dp),
                fontSize = 18.sp,
                text = movie.data[0].name
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreMovieCard() {
    //MovieCard()
}

