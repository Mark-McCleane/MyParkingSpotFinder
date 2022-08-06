package com.plcoding.mapscomposeguide.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ParkingSpotEntity(val latitude: Double,val longitude: Double, @PrimaryKey val ParkingSpotId: Int? = null) {

}