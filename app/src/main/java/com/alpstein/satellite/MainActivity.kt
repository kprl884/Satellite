package com.alpstein.satellite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alpstein.satellite.app.detail.DetailScreen
import com.alpstein.satellite.app.detail.DetailScreenViewModel
import com.alpstein.satellite.app.list.ListScreen
import com.alpstein.satellite.app.list.ListScreenViewModel
import com.alpstein.satellite.ui.theme.SatelliteTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalTextApi
@AndroidEntryPoint
@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SatelliteTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val detailScreenViewModel: DetailScreenViewModel by viewModels()
                    val listScreenViewModel: ListScreenViewModel by viewModels()

                    NavHost(navController = navController, startDestination = "list") {
                        composable("list") {
                            ListScreen(
                                listScreenViewModel.uiState,
                                navController
                            )
                        }
                        composable("detail/{detailId}") { navBackStackEntry ->
                            val detailId = navBackStackEntry.arguments?.getString("detailId")
                            detailId?.let {
                                DetailScreen(
                                    detailScreenViewModel.getDetailSatellite(id = it.toInt())
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}