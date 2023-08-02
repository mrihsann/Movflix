package com.ihsanarslan.movflix.presentation.movies.views.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.ihsanarslan.movflix.domain.model.Movie

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MovieListRow(
    movie : Movie,
    onItemClick : (Movie) -> Unit
) {


    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable { onItemClick(movie) }
        .padding(10.dp)
        .height(200.dp),
        shape = RoundedCornerShape(20.dp))
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
        ) {

            Image(
                painter = rememberImagePainter(data = movie.Poster),
                contentDescription = movie.Title,
                modifier = Modifier
                    .size(135.dp, 200.dp)
                    .clip(RoundedCornerShape(20.dp, 0.dp, 75.dp, 20.dp))
            )


            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    movie.Title,
                    style = MaterialTheme.typography.bodyLarge,
                    overflow = TextOverflow.Ellipsis,
                    color = Black,
                    textAlign = TextAlign.Center
                )
                //tarih
                Text(
                    movie.Year,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 15.sp),
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray,
                )


                //tür
                Text(
                    text = movie.Type,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 15.sp),
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray,
                )

            }

        }
    }
}