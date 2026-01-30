package com.slivsound.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.slivsound.R

@Composable
fun IconButton(
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(R.drawable.ic_plus),
        contentDescription = null,
        modifier = Modifier
            .size(64.dp)
            .clickable { onClick() }
    )
}

@Preview(showBackground = true)
@Composable
fun ShowIconButton() {
    IconButton(
        onClick = {}
    )
}