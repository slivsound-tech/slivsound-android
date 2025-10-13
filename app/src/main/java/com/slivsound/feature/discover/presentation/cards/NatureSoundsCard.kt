package com.slivsound.feature.discover.presentation.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.slivsound.R
import com.slivsound.feature.discover.domain.SoundModel
import com.slivsound.ui.components.Badge

@Composable
fun NatureSoundsCard(item: SoundModel, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(MaterialTheme.shapes.large)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = ImageRequest.Builder(context)
                .crossfade(true)
                .data(item.imageUrl)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Box(
            Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Transparent, Color.Black.copy(alpha = 0.55f))
                    )
                )
        )
        Column(
            modifier = Modifier
                .padding(start = 8.dp, bottom = 8.dp)
                .align(Alignment.BottomStart)
        ) {
            Badge(icon = painterResource(R.drawable.ic_music))
            Text(
                item.title,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(Modifier.height(4.dp))
            Text(
                item.description,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
