package com.sunbio.composemvi.ui.movie

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.sunbio.composemvi.R
import com.sunbio.composemvi.api.Movie

@Composable
fun MovieScreen(
    viewModel: MovieViewModel = viewModel()
) {
    val data = viewModel.fetchLastedMovies().collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(data) { item ->
            MovieCard(movie = item!!)
        }
    }
}

@Composable
fun MovieCard(movie: Movie) {
    var backgroundColor by remember {
        mutableStateOf(Color.Black)
    }
    Card(
        elevation = 6.dp,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = backgroundColor),
        ) {
            AsyncImage(modifier = Modifier
                .height(180.dp)
                .width(120.dp)
                .padding(12.dp),
                model = ImageRequest.Builder(LocalContext.current).data(movie.poster)
                    .crossfade(true).allowHardware(false).build(),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                onState = {
                    if (it is AsyncImagePainter.State.Success) {
                        backgroundColor = Color(
                            Palette.from(it.result.drawable.toBitmap()).generate()
                                .getMutedColor(0xFFFFFF)
                        )
                    }
                })

            Row(
                modifier = Modifier.fillMaxSize()
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.7f)
                ) {
                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        color = Color.White,
                        fontSize = 15.sp,
                        text = movie.name ?: ""
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color.White,
                        text = movie.alias ?: "",
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 11.sp,
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color.White,
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis,
                        text = movie.description ?: "",
                        fontSize = 11.sp,
                    )
                }

                Canvas(
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .weight(0.01f)
                        .fillMaxHeight()
                ) {
                    drawLine(
                        color = Color.White,
                        start = Offset(0f, 0f),
                        end = Offset(0f, size.height),
                        strokeWidth = 2f,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f),
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(0.25f)
                        .padding(start = 8.dp, top = 16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Image(
                            modifier = Modifier
                                .size(15.dp),
                            painter = painterResource(id = R.drawable.logo_douban),
                            contentDescription = null
                        )
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 4.dp),
                            color = Color.White,
                            text = "豆瓣",
                            fontSize = 10.sp
                        )
                    }

                    Text(
                        text = movie.doubanRating ?: "",
                        color = Color.White,
                        fontSize = 10.sp
                    )
                }
            }

        }
    }
}


@Preview(
    widthDp = 400, heightDp = 200, showBackground = true
)
@Composable
fun PreMovieCard(
    @PreviewParameter(MoviePreviewCardParamProvider::class) movie: Movie
) {
    MovieCard(movie)
}

class MoviePreviewCardParamProvider : PreviewParameterProvider<Movie> {
    override val values = sequenceOf(
        Movie(
            poster = "https://wmdb.querydata.org/movie/poster/1603701754760-c50d8a.jpg",
            name = "肖生克的救赎",
            alias = "月黑高飞(港) / 刺激1995(台) / 地狱诺言 / 铁窗岁月 / 消香克的救赎",
            description = "20世纪40年代末，小有成就的青年银行家安迪（蒂姆·罗宾斯 Tim Robbins 饰）因涉嫌杀害妻子及她的情人而锒铛入狱。在这座名为鲨堡的监狱内，希望似乎虚无缥缈，终身监禁的惩罚无疑注定了安迪接下来...",
            doubanRating = "10"
        )
    )
}


