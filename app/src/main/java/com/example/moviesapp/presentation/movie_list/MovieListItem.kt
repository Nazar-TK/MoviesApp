package com.example.moviesapp.presentation.movie_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviesapp.R
import com.example.moviesapp.domain.model.Movie

@Composable
fun MovieListItem(
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {

    Column(
        modifier = Modifier.clickable { onItemClick(movie) }
    ) {

        AsyncImage(
            model = movie.posterUrl,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movie.originalTitle
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movie.releaseYear
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.Start){
            Image(
                painter = painterResource(id = R.drawable.star_ic),
                contentDescription = "Rating",
                modifier = Modifier.size(30.dp).padding(end = 8.dp)
            )
            Text(
                text = movie.voteAverage.toString()
            )
        }
    }
}