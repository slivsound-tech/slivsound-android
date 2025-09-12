package com.slivsound.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.slivsound.ui.theme.SlivsoundTheme

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false
) {
    val realEnabled = enabled && !loading
    SlivsoundTheme {
        Button(
            onClick = onClick,
            enabled = realEnabled,
            modifier = modifier.height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f)
            )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(18.dp),
                        strokeWidth = 2.dp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(Modifier.size(8.dp))
                    Text("Loading…", style = MaterialTheme.typography.labelLarge)
                } else {
                    Text(text, style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrimaryButtonPreview() {

    SlivsoundTheme {

        PrimaryButton(
            text = "Primary Disabled",
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrimaryButtonLoadingPreview() {

    SlivsoundTheme {

        PrimaryButton(
            text = "Primary Loading",
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrimaryButtonEnabledPreview() {

    SlivsoundTheme {

        PrimaryButton(
            text = "Primary Enabled",
            onClick = {}
        )
    }
}

