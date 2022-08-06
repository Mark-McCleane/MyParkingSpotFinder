package com.plcoding.mapscomposeguide.Data

import com.plcoding.mapscomposeguide.Domain.Model.ParkingSpot
import com.plcoding.mapscomposeguide.Domain.Repository.ParkingSpotRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ParkingSpotRepositoryImplementation(private val dao: ParkingSpotDao):ParkingSpotRepository {
    override suspend fun insertParkingSpot(spot: ParkingSpot) {
    dao.insertParkingSpot(spot.toParkingSpotEntity())
    }

    override suspend fun deleteParkingSpot(spot: ParkingSpot) {
        dao.deleteParkingSpot(spot.toParkingSpotEntity())
    }

    override fun getParkingSpots(): Flow<List<ParkingSpot>> {
        return dao.getParkingSpot().map { parkingspots ->
            parkingspots.map { it.toParkingSpot() }
        }
    }
}