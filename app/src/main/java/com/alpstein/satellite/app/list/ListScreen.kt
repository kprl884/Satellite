package com.alpstein.satellite.app.list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alpstein.satellite.domain.entity.Satellite
import kotlinx.coroutines.flow.StateFlow

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    uiStateFlow: StateFlow<ListScreenUIState>?,
    navController: NavController?
) {
    var searchQuery by remember { mutableStateOf("") }
    val list = uiStateFlow?.value?.satelliteList
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        var text by remember { mutableStateOf("Search") }

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White),
            value = text,
            onValueChange = { text = it },
        )

        Spacer(modifier = Modifier.height(16.dp))
        if (list != null) {
            if(list.isNotEmpty()){
                LazyColumn {
                    items( list) { satellite ->
                        SatelliteItem(satellite) {
                            navController?.navigate(
                                "detail/{detailId}"
                                    .replace(
                                        oldValue = "{detailIdId}",
                                        newValue = "${satellite.id}"
                                    )
                            )
                        }
                        Divider(color = Color.Gray)
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewListScreen() {

}
@Composable
fun SatelliteItem(satellite: Satellite, onItemClick: (Satellite) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onItemClick(satellite) },
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
                .background(color = Color.DarkGray)
                .fillMaxSize()
        ) {
            satellite.name?.let { Text(text = it, style = MaterialTheme.typography.bodyLarge,
                color = Color.White) }
            Text(
                text = if (satellite.active) "Active" else "Inactive",
                color = if (satellite.active) Color.Green else Color.Red
            )
        }
    }
}

@Preview
@Composable
fun ListScreenPreview() {
    val satellites = listOf(
        Satellite(1, false, "Starship-1"),
        Satellite(2, true, "Dragon-1"),
        Satellite(3, true, "Starship-3")
    )

    ListScreen(null, navController = null)
}

