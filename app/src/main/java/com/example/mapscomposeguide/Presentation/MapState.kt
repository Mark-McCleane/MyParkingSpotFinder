package com.plcoding.mapscomposeguide.Presentation

import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties
import com.plcoding.mapscomposeguide.Domain.Model.ParkingSpot
import java.util.*

data class MapState(
    val properties: MapProperties = MapProperties(),
    val parkingSpotList : List<ParkingSpot> = emptyList(),
    val isFalloutMap: Boolean = false,
)