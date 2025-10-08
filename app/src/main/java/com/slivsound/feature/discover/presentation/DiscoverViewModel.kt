package com.slivsound.feature.discover.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slivsound.feature.discover.domain.model.Melody
import com.slivsound.feature.discover.domain.repository.DiscoverRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel


@KoinViewModel
class DiscoverViewModel(
    private val repository: DiscoverRepository
) : ViewModel() {


    private val _state = MutableStateFlow<List<Melody>>(emptyList())
    val state: StateFlow<List<Melody>> = _state

//    private val _loading = MutableStateFlow(false)
//    val loading: StateFlow<Boolean> = _loading
//
//    private val _error = MutableStateFlow<String?>(null)
//    val error: StateFlow<String?> = _error

    init {

        load()
    }

    fun load() = viewModelScope.launch {
        repository.fetchMelodies()
            .onSuccess { _state.value = it }
//            .onFailure { _error.value = it.message ?: "Unknown error" }

//        _loading.value = false
    }


}
