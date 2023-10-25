package com.alpstein.satellite.app.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpstein.satellite.domain.usecase.GetSatelliteByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailScreenViewModel @Inject constructor(
    private val getItemByIdUseCase: GetSatelliteByIdUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(DetailScreenUIState())
    private val uiState: StateFlow<DetailScreenUIState> = _uiState.asStateFlow()

    fun getDetailSatellite(id :Int): StateFlow<DetailScreenUIState>{
        viewModelScope.launch(Dispatchers.IO) {
            getItemByIdUseCase.invoke(id) { data ->
                _uiState.update { currentState ->
                    currentState.copy(
                        satellite = data
                    )
                }
            }
        }
        return uiState
    }
}