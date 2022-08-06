package com.plcoding.mapscomposeguide.Presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker

@Composable
fun MapScreen(
    viewModel: MapsViewModel = viewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }
    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(MapEvent.toogleFalloutMap)
            }) {
                Icon(
                    imageVector = if (viewModel.state.isFalloutMap) {
                        Icons.Default.ToggleOff
                    } else Icons.Default.ToggleOn,
                    contentDescription = "Toggle Fallout Mode"
                )
            }
        }
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = viewModel.state.properties,
            uiSettings = uiSettings,
            onMapLongClick = {
                viewModel.onEvent(MapEvent.OnMapLongClick(it))
            }
        ) {
            viewModel.state.parkingSpotList.forEach { spot ->
                Marker(
                    position = LatLng(spot.latitude, spot.longitude),
                    title = "Parking Spot (${spot.latitude}, ${spot.longitude})",
                    snippet = "Long Click To Delete",
                    onInfoWindowLongClick = {
                        viewModel.onEvent((MapEvent.OnInfoWindowLongClick(spot)))
                    },
                    onClick = {
                        it.showInfoWindow()
                        true
                    },
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
                )
            }
        }
    }
}