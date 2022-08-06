package com.plcoding.mapscomposeguide.Data

import com.plcoding.mapscomposeguide.Domain.Model.ParkingSpot

fun ParkingSpotEntity.toParkingSpot(): ParkingSpot {
    return ParkingSpot(latitude = latitude, longitude = longitude, ParkingSpotId = ParkingSpotId)
}

fun ParkingSpot.toParkingSpotEntity(): ParkingSpotEntity {
    return ParkingSpotEntity(latitude = latitude,
        longitude = longitude,
        ParkingSpotId = ParkingSpotId)
}