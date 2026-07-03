package io.jadu.phoenix.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.jadu.phoenix.storage.StorageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okio.Path
import kotlin.random.Random

class StorageViewModel(
    private val repository: StorageRepository
) : ViewModel() {
    private val _state = MutableStateFlow(StorageUiState())
    val state: StateFlow<StorageUiState> = _state.asStateFlow()

    private val folder = "images"

    init {
        refresh()
    }

    fun refresh(){
        viewModelScope.launch {
            _state.update { it.copy(loading = true, error = null) }
            runCatching { repository.list(folder) }
                .onSuccess { item ->
                    _state.update { it.copy( loading = false, items = item) }
                }
                .onFailure { e ->
                    _state.update { it.copy(loading = false, error = e.message) }
                }
        }
    }

    fun uploadImage(bytes: ByteArray) {
        val name = "img-${Random.nextLong(0, Long.MAX_VALUE)}.jpg"
        viewModelScope.launch {
            _state.update { it.copy(loading = true, error = null, lastUrl = null) }
            runCatching { repository.upload("$folder/$name", bytes) }
                .onSuccess { url ->
                    _state.update { it.copy(uploading = false, lastUrl = url) }
                    refresh()
                }
                .onFailure { e->
                    _state.update { it.copy(uploading = false, error = e.message) }
                }
        }
    }

    fun delete(path: String) {
        viewModelScope.launch {
            runCatching { repository.delete(path) }
                .onSuccess { refresh() }
                .onFailure { e -> _state.update { it.copy(error = e.message) } }
        }
    }

}

data class StorageUiState(
    val loading: Boolean = false,
    val uploading: Boolean = false,
    val items: List<String> = emptyList(),
    val lastUrl: String? = null,
    val error: String? = null
)