package com.plcoding.mapscomposeguide.Data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ParkingSpotDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParkingSpot(spot: ParkingSpotEntity)

    @Delete
    suspend fun deleteParkingSpot(spot: ParkingSpotEntity)

    @Query("Select * from parkingspotentity")
    fun getParkingSpot(): Flow<List<ParkingSpotEntity>>
}