package com.slivsound.feature.discover

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.slivsound.R
import com.slivsound.ui.components.Badge
import com.slivsound.ui.components.Card
import com.slivsound.ui.components.SearchField
import com.slivsound.ui.theme.SlivsoundTheme

@Composable
fun DiscoverScreen(
) {
    DiscoverView()
}

@Composable
fun DiscoverView() {
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
                text = stringResource(R.string.tab_discover),
                style = MaterialTheme.typography.headlineMedium
            )

            SlivsoundTheme {
                SearchField(
                    modifier = Modifier
                        .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp),
                    placeholder = "Search...",
                    value = query,
                    onValueChange = { query = it },
                    onSearch = { println("Search: $query") }
                )
            }
            Spacer(Modifier.height(16.dp))
            val columns = 3
            val items = List(7) {}
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
                        text = stringResource(R.string.melodies),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(Modifier.height(16.dp))

                }
                items(items.chunked(columns)) { row ->
                    Row(horizontalArrangement = Arrangement.spacedBy(19.dp)) {
                        SlivsoundTheme() {
                            row.forEach { item ->
                                Card(
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(1f),
                                ) {
                                    Box(
                                        Modifier
                                            .fillMaxSize()
                                            .clip(MaterialTheme.shapes.large)
                                    ) {
                                        Image(
                                            painter = painterResource(R.drawable.img),
                                            contentDescription = null,
                                            modifier = Modifier.matchParentSize(),
                                            contentScale = ContentScale.Crop
                                        )

                                        Box(
                                            Modifier
                                                .align(Alignment.BottomStart)
                                                .fillMaxWidth()
                                                .fillMaxHeight(0.9f)
                                                .background(
                                                    Brush.verticalGradient(
                                                        listOf(
                                                            Color.Transparent,
                                                            Color.Black.copy(alpha = 0.55f)
                                                        )
                                                    )
                                                )
                                        )
                                        Column(
                                            modifier = Modifier
                                                .padding(start = 8.dp)
                                                .align(Alignment.BottomStart)
                                        ) {
                                            Badge(icon = painterResource(R.drawable.ic_music))
                                            Text(
                                                text = "Name",
                                                color = MaterialTheme.colorScheme.onPrimary,
                                                style = MaterialTheme.typography.titleSmall
                                            )
                                            Spacer(Modifier.height(4.dp))
                                            Text(
                                                text = "adsfgadfvDFSVADFVAsdv",
                                                color = MaterialTheme.colorScheme.onSurface,
                                                style = MaterialTheme.typography.bodySmall
                                            )
                                        }
                                    }
                                }
                                repeat(columns - row.size) { Spacer(Modifier.weight(1f)) }
                            }
                        }
                    }
                }
                item {
                    Text(
                        text = stringResource(R.string.nature_Sounds),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(Modifier.height(16.dp))
                }
                items(items.chunked(columns)) { row ->
                    Row(horizontalArrangement = Arrangement.spacedBy(19.dp)) {
                        SlivsoundTheme() {
                            row.forEach { item ->
                                Card(
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(1f)
                                ) {

                                    Column() {
                                        Badge(icon = painterResource(R.drawable.ic_music))
                                        Text(
                                            text = "Name",
                                            color = MaterialTheme.colorScheme.onPrimary,
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                        Spacer(Modifier.height(4.dp))
                                        Text(
                                            text = "adsfgadfvDFSVADFVAsdv",
                                            color = MaterialTheme.colorScheme.onPrimary,
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                    }
                                }
                            }
                            repeat(columns - row.size) { Spacer(Modifier.weight(1f)) }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiscoverPreview() {
    SlivsoundTheme {
        DiscoverView()
    }
}

