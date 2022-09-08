package com.example.appatemporal.data.di

import android.content.Context
import androidx.room.Room
import com.example.appatemporal.data.localdatabase.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val DATABASE_NAME = "local_database"
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, LocalDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDao(db: LocalDatabase){
        db.areaDao()
        db.actividadDao()
        db.costoDao()
        db.estatusDao()
        db.objetivoDao()
        db.proyectoDao()
    }
}