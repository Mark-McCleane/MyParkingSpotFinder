package com.plcoding.mapscomposeguide.DependecyInjection

import android.app.Application
import androidx.room.Room
import com.plcoding.mapscomposeguide.Data.ParkingSpotDatabase
import com.plcoding.mapscomposeguide.Data.ParkingSpotRepositoryImplementation
import com.plcoding.mapscomposeguide.Domain.Repository.ParkingSpotRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesParkingSpotDatabase(app: Application): ParkingSpotDatabase {
        return Room.databaseBuilder(app,
            ParkingSpotDatabase::class.java, "ParkingSpotDB.db")
            .build()
    }

    @Singleton
    @Provides
    fun providesParkingSpotRepository(db: ParkingSpotDatabase): ParkingSpotRepository{
        return ParkingSpotRepositoryImplementation(db.dao)
    }
}