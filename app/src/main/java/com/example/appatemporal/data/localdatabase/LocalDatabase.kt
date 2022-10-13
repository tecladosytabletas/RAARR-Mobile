package com.example.appatemporal.data.localdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appatemporal.data.localdatabase.dao.*
import com.example.appatemporal.data.localdatabase.entities.*

@Database(entities = [Actividad::class,  Costo::class, Estatus::class,
                        Proyecto::class, Usuario::class], version = 1)
                        
abstract class LocalDatabase: RoomDatabase() {
    abstract val actividadDao: ActividadDao
    abstract val costoDao: CostoDao
    abstract val estatusDao: EstatusDao
    abstract val proyectoDao: ProyectoDao
    abstract val usuarioDao: UsuarioDao


    companion object{
        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase{
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                    context,
                    LocalDatabase::class.java,
                    "local_database"
                ).build().also { INSTANCE = it }
            }
        }
    }



    /*  abstract fun actividadDao(): ActividadDao
      abstract fun areaDao(): AreaDao
      abstract fun costoDao(): CostoDao
      abstract fun estatusDao(): EstatusDao
      abstract fun objetivoDao(): ObjetivoDao
      abstract fun proyectoDao(): ProyectoDao*/
}
