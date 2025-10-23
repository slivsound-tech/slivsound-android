package com.slivsound.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.slivsound.R
import com.slivsound.ui.theme.SlivsoundTheme

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String? = null,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.bodyLarge,
        placeholder = {
            if (placeholder != null)
                Text(text = placeholder)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(24.dp)
            )
        },
        trailingIcon = {
            if (value.isNotEmpty()) {
                Icon(
                    painter = painterResource(R.drawable.ic_clear),
                    contentDescription = "Очистить",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { onValueChange("") }
                )
            }
        },
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.outline,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
            disabledIndicatorColor = MaterialTheme.colorScheme.outline,
            errorIndicatorColor = MaterialTheme.colorScheme.error,

            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            errorContainerColor = MaterialTheme.colorScheme.surface,

            cursorColor = MaterialTheme.colorScheme.onSurface,
            focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { onSearch() }
        )
    )
}



@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    var query by remember { mutableStateOf("") }

    SlivsoundTheme {
        SearchField(
            value = query,
            onValueChange = { query = it },
            onSearch = { println("Search: $query") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchV2Preview() {
    var query by remember { mutableStateOf("") }

    SlivsoundTheme {
        SearchField(
            placeholder = "Search sounds",
            value = query,
            onValueChange = { query = it },
            onSearch = { println("Search: $query") }
        )
    }
}
