package com.example.appatemporal.data.localdatabase.dao

import androidx.room.*
import com.example.appatemporal.data.localdatabase.entities.Proyecto


/**
 * Interface for ProyectoDAO
 */
@Dao
interface ProyectoDao {
    /**
     * Function for getting all projects
     * @return: list of Proyecto objects
     */
    @Query("SELECT * FROM proyecto_table")
    suspend fun getAll(): List<Proyecto>


    /**
     * Function for adding one project
     * @param proyecto: Proyecto object
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(proyecto: Proyecto)

    /**
     * Function for getting one project by id
     * @param id : Integer id of project
     * @return: Proyecto object
     */
    @Query("SELECT * FROM proyecto_table WHERE id_proyecto = :id")
    suspend fun getById(id: Int): Proyecto


    /**
     * Function for deleting one project
     * @param proyecto: Proyecto object
     */
    @Delete
    suspend fun delete(proyecto: Proyecto)

    /**
     * Function for updating one project
     * @param proyecto: Proyecto object
     */
    @Update
    suspend fun update(proyecto: Proyecto)

    /**
     * Function for filtering projects by status
     * @param stringStatus: Boolean
     * @return: List of Proyecto objects
     */
    @Query("SELECT * FROM proyecto_table WHERE estatus_completado = :stringStatus")
    suspend fun FilterProjectsByStatus (stringStatus: Boolean): List<Proyecto>

    /**
     * Function for updating budget of a project
     * @param presupuestoNew: Double of amount of budget.
     * @param id: Integer project id
     */
    @Query("UPDATE proyecto_table SET presupuesto=:presupuestoNew WHERE id_proyecto = :id")
    suspend fun updatePresupuesto(presupuestoNew: Double, id: Int)

    /**
     * Function for updating goal of a project
     * @param metaNew: Double of amount of expected goal.
     * @param id: Integer project id
     */
    @Query("UPDATE proyecto_table SET meta=:metaNew WHERE id_proyecto = :id")
    suspend fun updateMeta(metaNew: Double, id: Int)

    /**
     * Function for updating status
     * @param estatusNew: Boolean.
     * @param id: Integer project id
     */
    @Query("UPDATE proyecto_table SET estatus_completado=:estatusNew WHERE id_proyecto = :id")
    suspend fun updateEstatusCompletado(estatusNew: Boolean, id: Int)

    /**
     * Function for updating name and date of a project
     * @param name: String, name of the project
     * @param date: String, date of the project
     * @param time: String, time of a project
     * @param id: Integer, id of project
     */
    @Query("UPDATE proyecto_table SET nombre_proyecto = :name, fecha_inicio = :date,created_at= :time WHERE id_proyecto =:id")
    suspend fun updateModify(name: String, date: String, time:String ,id: Int)

}