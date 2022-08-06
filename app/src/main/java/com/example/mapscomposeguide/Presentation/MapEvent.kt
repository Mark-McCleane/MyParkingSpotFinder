package com.plcoding.mapscomposeguide.Presentation

import com.google.android.gms.maps.model.LatLng
import com.plcoding.mapscomposeguide.Domain.Model.ParkingSpot

sealed class MapEvent {
    object toogleFalloutMap : MapEvent()
    data class OnMapLongClick(val latitudeLongitude: LatLng): MapEvent()
    data class OnInfoWindowLongClick(val spot: ParkingSpot): MapEvent()
}