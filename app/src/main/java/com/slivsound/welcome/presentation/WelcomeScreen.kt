package com.slivsound.welcome.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.slivsound.featurename.presentation.NameEffect
import com.slivsound.featurename.presentation.NameEvent
import com.slivsound.featurename.presentation.NameState
import com.slivsound.featurename.presentation.NameViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    viewModel: WelcomeViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effects.collectLatest { effect ->
            when (effect) {
                is NameEffect.ShowError -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }

                is NameEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    WelcomeView(
        modifier = modifier,
        state = state,
        listener = object : NameClickListener {
            override fun onBack() {}
            override fun onTest() {
                viewModel.onEvent(NameEvent.SetExample("Test Hello!"))
            }
        }
    )
}

@Composable
fun WelcomeView(
    modifier: Modifier,
    state: NameState,
    listener: NameClickListener,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = state.name)
        Button(onClick = {
            listener.onBack()
        }) {
            Text("Back")
        }
        Button(onClick = {
            listener.onTest()
        }) {
            Text("Test")
        }
    }
}

interface NameClickListener {
    fun onBack()
    fun onTest()
}

@Preview(showBackground = true)
@Composable
fun NameViewPreview() {
    NameView(
        modifier = Modifier,
        state = NameState(
            name = "Test"
        ),
        listener = object : NameClickListener {
            override fun onBack() {}
            override fun onTest() {}
        }
    )
}