package com.plcoding.mapscomposeguide.Presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.MapStyleOptions
import com.plcoding.mapscomposeguide.Domain.Model.ParkingSpot
import com.plcoding.mapscomposeguide.Domain.Repository.ParkingSpotRepository
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel  @Inject constructor(private val repo: ParkingSpotRepository): ViewModel() {
    var state by mutableStateOf(MapState())

    init {
        viewModelScope.launch { repo.getParkingSpots().collectLatest { parkingspots ->
            state = state.copy(
                parkingSpotList = parkingspots
            )
        } }
    }
    fun onEvent(onEvent: MapEvent) {
        when (onEvent) {
            is MapEvent.toogleFalloutMap -> {
                state = state.copy(
                    properties = state.properties.copy(
                        mapStyleOptions = if (state.isFalloutMap) {
                            null
                        } else {
                            MapStyleOptions(MapStyle.json)
                        }
                    ),
                    isFalloutMap = !state.isFalloutMap

                )
            }
            is MapEvent.OnMapLongClick -> {
                viewModelScope.launch {
                    repo.insertParkingSpot(ParkingSpot(
                        onEvent.latitudeLongitude.latitude,
                        onEvent.latitudeLongitude.longitude
                        ))
                }
            }
            is MapEvent.OnInfoWindowLongClick -> {
                viewModelScope.launch { repo.deleteParkingSpot(onEvent.spot) }
            }
        }
    }
}