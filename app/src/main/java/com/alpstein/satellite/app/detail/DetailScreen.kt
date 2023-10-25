package com.alpstein.satellite.app.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.StateFlow

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DetailScreen(
    uiState: StateFlow<DetailScreenUIState>
) {
        Column {
            //uiState.value.satellite.name?.let { Text(text = it) }
        }
}

@Composable
@Preview(showBackground = true)
fun PreviewDetailScreen() {
}