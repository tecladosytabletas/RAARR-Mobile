package com.example.appatemporal.data.localdatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.appatemporal.data.localdatabase.entities.Actividad
import com.example.appatemporal.data.localdatabase.entities.Proyecto


@Dao
interface ActividadDao {

    /**
     * Function for getting all activities of a project
     * @param id: Int project id
     * @return: List of activities
     */
    @Query("SELECT * FROM actividad_table WHERE id_proyecto = :id")
    suspend fun getAllActivityId(id: Int): List<Actividad>


    /**
     * Function for getting all existent activities
     * @return: List of activities
     */
    @Query("SELECT * FROM actividad_table")
    suspend fun getAll(): List<Actividad>

    /**
     * Function for adding a new activity
     * @param actividad: ActividadModel object
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(actividad: Actividad)

    /**
     * Function for getting an activity by id
     * @param id: Int activity id.
     * @return Actividad: ActividadModel object
     */
    @Query("SELECT * FROM actividad_table WHERE id_actividad = :id")
    suspend fun getById(id: Int): Actividad

    /**
     * Function for counting pending activities
     * @param stringStatus: status name
     * @return: Int, total count of pending activities.
     */
    @Query("SELECT COUNT(*) FROM actividad_table WHERE id_proyecto = :id_a AND estatus = :stringStatus")
    suspend fun countPendingActivities(id_a: Int, stringStatus: String): Int

    /**
     * Function for counting pending activities
     * @param stringStatus: status name
     * @return: Int, total count of done activities.
     */
    @Query("SELECT COUNT(*) FROM actividad_table WHERE id_proyecto = :id_a AND estatus = :stringStatus")
    suspend fun countDoneActivities(id_a: Int, stringStatus: String): Int

    /**
     * Function for getting an activity by status
     * @param id_pro: Int project id.
     * @param stringStatus: String, status name
     * @return Actividad: ActividadModel object
     */
    @Query("SELECT * FROM actividad_table WHERE id_proyecto = :id_pro AND estatus = :stringStatus")
    suspend fun FilterActivityByStatus (id_pro: Int, stringStatus: String): List<Actividad>

    /**
     * Function for counting all activities of a project
     * @param id_a: Int, project id.
     * @return: Int of total count of activities.
     */
    @Query("SELECT COUNT(*) FROM actividad_table WHERE id_proyecto = :id_a")
    suspend fun countAllActivities(id_a: Int): Int

    /**
     * Function for getting an activity by area
     * @param id_pro: Int project id.
     * @param stringArea: String, area name
     * @return Actividad: List of ActividadModel
     */
    @Query("SELECT * FROM actividad_table WHERE id_proyecto = :id_pro AND area = :stringArea")
    suspend fun FilterActivityByArea (id_pro: Int, stringArea: String): List<Actividad>

    /**
     * Function for getting an activity by priority
     * @param id_pro: Int project id.
     * @param stringPriority: String, priority name
     * @return Actividad: List of ActividadModel
     */
    @Query("SELECT * FROM actividad_table WHERE id_proyecto = :id_pro AND prioridad = :stringPriority")
    suspend fun FilterActivityByPriority (id_pro: Int, stringPriority: String): List<Actividad>

    /**
     * Function for deleting an activity
     * @param actividad: ActividadModel
     */
    @Delete
    suspend fun delete(actividad: Actividad)

    /**
     * Function for updating an activity
     * @param nombre: String, name of activity
     * @param estatus: String, status.
     * @param area: String, area.
     * @param prioridad: String, priority.
     * @param id: Int, activity id
     */
    @Query("UPDATE actividad_table SET nombre_actividad = :nombre,  estatus = :estatus, area = :area, prioridad = :prioridad  WHERE id_actividad = :id")
    suspend fun update(nombre:String, estatus:String, area:String, prioridad:String, id: Int)


}