package com.alpstein.satellite.app.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpstein.satellite.domain.usecase.ListGetLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel  @Inject constructor(
    private val listGetUseCase: ListGetLocalUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ListScreenUIState())
    val uiState: StateFlow<ListScreenUIState> = _uiState.asStateFlow()

    init {
        fillListEmpty()
    }

    private fun fillListEmpty() {
        viewModelScope.launch(Dispatchers.IO) {
            listGetUseCase.invoke { dataList ->
                _uiState.update { currentState ->
                    currentState.copy(
                        isListLoading = false,
                        satelliteList = dataList
                    )
                }
            }
        }
    }
}