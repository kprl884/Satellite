package com.alpstein.satellite.app.detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailScreenViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(DetailScreenUIState())
    val uiState: StateFlow<DetailScreenUIState> = _uiState.asStateFlow()
}