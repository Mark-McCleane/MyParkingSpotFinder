package com.plcoding.mapscomposeguide.Domain.Repository

import com.plcoding.mapscomposeguide.Domain.Model.ParkingSpot
import kotlinx.coroutines.flow.Flow

interface ParkingSpotRepository {
    suspend fun insertParkingSpot(spot: ParkingSpot)

    suspend fun deleteParkingSpot(spot: ParkingSpot)

    fun getParkingSpots(): Flow<List<ParkingSpot>>
}