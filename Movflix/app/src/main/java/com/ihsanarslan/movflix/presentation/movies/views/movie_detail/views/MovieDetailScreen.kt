package com.ihsanarslan.movflix.presentation.movies.views.movie_detail.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.ihsanarslan.movflix.presentation.movies.views.movie_detail.MovieDetailViewModel


@Composable
fun MovieDetailScreen(
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel(),navController: NavController
){
    val state = movieDetailViewModel.state.value
    val scrollState = rememberScrollState()
    Box(modifier = Modifier.fillMaxSize()) {

        Box {
            state.movie?.let {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(600.dp)
                            .graphicsLayer {
                                alpha =
                                    1f - (scrollState.value.toFloat() / (scrollState.maxValue * 10f))
                                translationY = 0.8f * scrollState.value
                            }, contentAlignment = Center
                    ) {

                        Image(
                            painter = rememberImagePainter(data = it.Poster),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Box(modifier = Modifier.fillMaxSize()) {
                        Card(modifier = Modifier
                            .fillMaxSize()
                            .offset(0.dp, (-30).dp),shape = RoundedCornerShape(30.dp,30.dp)) {
                            Column(modifier = Modifier
                                .fillMaxSize()
                                .background(White)) {

                                Text(
                                    text = it.Title,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Black,
                                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 45.dp)
                                )

                                Text(
                                    text = it.Released,
                                    fontSize = 16.sp,
                                    color = Gray,
                                    modifier = Modifier.padding(start = 20.dp, end = 20.dp,top = 8.dp)
                                )
                                Text(
                                    text = "${it.Genre} - ${it.Runtime}",
                                    fontSize = 16.sp,
                                    color = Gray,
                                    modifier = Modifier.padding(start = 20.dp, end = 20.dp,top = 3.dp)
                                )

                                Text(
                                    text = "Actors : ${it.Actors}",
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(start = 20.dp, end = 20.dp,top = 5.dp)
                                )

                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, end = 20.dp,),verticalAlignment = Alignment.CenterVertically) {
                                    RatingBar(rating = it.imdbRating.toFloat())
                                    Text(
                                        text = it.imdbRating,
                                        fontSize = 18.sp,
                                        color = Black,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp)
                                    )
                                    Text(
                                        text = "(${it.imdbVotes})",
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(vertical = 3.dp)
                                    )
                                }
                                Text(
                                    text = "Overview",
                                    fontSize = 30.sp,
                                    color = Black,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(start = 20.dp, end = 20.dp,top = 8.dp)
                                )
                                Text(
                                    text = it.Plot,
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(start = 20.dp, end = 20.dp,top = 5.dp)
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset((-40).dp, (-65).dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            PlayButton()
                        }
                    }


                }
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 20.dp)) {
            Icon(modifier = Modifier
                .size(30.dp)
                .clickable { navController.popBackStack() },
                imageVector = (Icons.Default.ArrowBack),// Geri butonu
                contentDescription = "Geri",
                tint = LightGray
            )
        }

    }


}


@Composable
fun PlayButton() {
    Button(
        onClick = { /* Oynatma işlemi burada gerçekleştirilebilir */ },
        modifier = Modifier.size(65.dp),
        colors = ButtonDefaults.buttonColors(Color.Red)
    ) {
        Icon(
            Icons.Default.Star,
            contentDescription = "Play Button",
            tint = White
        )
    }
}

@Composable
fun RatingBar(
    rating: Float,
    maxRating: Float = 10f,
    starCount: Int = 5,
    ratingColor: Color = Color.Red,
    emptyColor: Color = Color.Gray,
    modifier: Modifier = Modifier
) {
    val filledStars = (rating / maxRating * starCount).toInt()

    Row(modifier = modifier) {
        repeat(starCount) { index ->
            Icon(
                imageVector = if (index < filledStars) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = null,
                tint = if (index < filledStars) ratingColor else emptyColor
            )
        }
    }
}