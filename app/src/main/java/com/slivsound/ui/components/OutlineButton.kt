package com.slivsound.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.slivsound.ui.theme.SlivsoundTheme

@Composable
fun ButtonOutline(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false
) {
    val realEnabled = enabled && !loading

    OutlinedButton(
        onClick = onClick,
        enabled = realEnabled,
        modifier = modifier.height(48.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContentColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(18.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.size(8.dp))
                Text("Loading…", style = MaterialTheme.typography.labelLarge)
            } else {
                Text(text, style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OutlineButtonEnabledPreview() {

    SlivsoundTheme {
        ButtonOutline(
            text = "Outline Enabled",
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OutlineButtonLoadingPreview() {

    SlivsoundTheme {
        ButtonOutline(
            text = "Outline Loading",
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OutlineButtonDisabledPreview() {

    SlivsoundTheme {

        ButtonOutline(
            text = "Outline Disabled",
            onClick = {}
        )
    }
}