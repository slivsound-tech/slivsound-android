package com.slivsound.feature.discover.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.slivsound.R
import com.slivsound.feature.discover.domain.SoundModel
import com.slivsound.ui.components.Badge
import com.slivsound.ui.components.SearchField
import org.koin.androidx.compose.koinViewModel

@Composable
fun DiscoverScreen(
    viewModel: DiscoverViewModel = koinViewModel()
) {
    val melodies by viewModel.state.collectAsState()
    val natureSounds by viewModel.state1.collectAsState()
    val noiseforSleepCard by viewModel.state2.collectAsState()
    DiscoverView(
        items = melodies,
        items1 = natureSounds,
        items2 = noiseforSleepCard
    )
}

@Composable
fun DiscoverView(
    items: List<SoundModel>,
    items1: List<SoundModel>,
    items2: List<SoundModel>
) {
    var query by remember { mutableStateOf("") }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
        tonalElevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Text(
                modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
                text = stringResource(R.string.nav_item_discover),
                style = MaterialTheme.typography.headlineMedium
            )
            SearchField(
                modifier = Modifier
                    .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp),
                placeholder = stringResource(R.string.discover_screen_section_Search),
                value = query,
                onValueChange = { query = it },
                onSearch = { println("Search: $query") }
            )

            Spacer(Modifier.height(16.dp))
            val columns = 3
            LazyColumn(
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                item {
                    Text(
                        text = stringResource(R.string.discover_screen_section_melodies),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(Modifier.height(16.dp))
                }
                items(items.chunked(columns)) { row ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(19.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        row.forEach { item ->
                            MelodiesCard(item, Modifier.weight(1f))
                        }
                        repeat(columns - row.size) { Spacer(Modifier.weight(1f)) }
                    }
                }
                item {
                    Text(
                        text = stringResource(R.string.discover_screen_section_nature_sounds),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(Modifier.height(16.dp))
                }
                items(items1.chunked(columns)) { row ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(19.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        row.forEach { item ->
                            NatureSoundsCard(item, Modifier.weight(1f))
                        }
                        repeat(columns - row.size) { Spacer(Modifier.weight(1f)) }
                    }
                }
                item {
                    Text(
                        text = stringResource(R.string.discover_screen_section_noise_for_sleep),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(Modifier.height(16.dp))
                }
                items(items2.chunked(columns)) { row ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(19.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        row.forEach { item ->
                            NoiseforSleepCard(item, Modifier.weight(1f))
                        }
                        repeat(columns - row.size) { Spacer(Modifier.weight(1f)) }
                    }
                }
            }
        }
    }
}

@Composable
private fun MelodiesCard(item: SoundModel, modifier: Modifier = Modifier) {
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

@Composable
private fun NatureSoundsCard(item: SoundModel, modifier: Modifier = Modifier) {
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

@Composable
private fun NoiseforSleepCard(item: SoundModel, modifier: Modifier = Modifier) {
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
            Badge(icon = painterResource(R.drawable.ic_left_sound_wave_1))
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

