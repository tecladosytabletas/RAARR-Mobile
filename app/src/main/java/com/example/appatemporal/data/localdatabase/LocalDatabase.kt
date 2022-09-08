package com.example.appatemporal.data.localdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appatemporal.data.localdatabase.dao.*
import com.example.appatemporal.data.localdatabase.entities.*

@Database(entities = [Actividad::class, Area::class, Costo::class, Estatus::class, Objetivo::class, Proyecto::class], version = 1)
abstract class LocalDatabase: RoomDatabase() {
    abstract fun actividadDao(): ActividadDao
    abstract fun areaDao(): AreaDao
    abstract fun costoDao(): CostoDao
    abstract fun estatusDao(): EstatusDao
    abstract fun objetivoDao(): ObjetivoDao
    abstract fun proyectoDao(): ProyectoDao
}
