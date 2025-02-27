package com.example.appatemporal.domain

import android.content.Context
import android.util.Log
import com.example.appatemporal.data.localdatabase.LocalDatabase
import com.example.appatemporal.data.localdatabase.entities.*
import com.example.appatemporal.domain.models.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

/**
 * Class that works as a repository for the application
 * following the Clean Architecture of MVVM
 * @param context: Context -> Context of the activities
 */
class Repository(context: Context) {

    // Firestore
    private val firestoreAPI = FirestoreService()

    /**
     * Asks Firestore Service to add a user and his role
     *
     * @param uid: String -> User Uid
     * @param user: UserModel -> Model to insert documents to Firestore
     * @param role: String -> Role selected by the user
     */
    suspend fun addUser(uid: String, user: UserModel, role: String) {
        firestoreAPI.addUser(uid, user)
        firestoreAPI.addUserRole(uid, role)
    }

    /**
     * Asks Firestore Service to verify the existence of a user
     *
     * @param uid: String -> User uid
     * @return Boolean -> Existence of the user
     */
    suspend fun verifyUser(uid: String) : Boolean {
        return firestoreAPI.verifyUser(uid)
    }

    /**
     * Asks Firestore Service to get the user information
     *
     * @param uid: String -> User uid
     * @return DocumentSnapshot -> query result from Firestore
     */
    suspend fun getUser(uid: String) : DocumentSnapshot{
        return firestoreAPI.getUser(uid)
    }

    /**
     * Asks Firestore Service to get the user role
     *
     * @param uid: String -> User uid
     * @return DocumentSnapshot -> query result from Firestore
     */
    suspend fun getUserRole(uid: String) : DocumentSnapshot {
        return firestoreAPI.getUserRole(uid)
    }

    /**
     * Asks Firestore Service to get the number of events a user has created
     *
     * @param uid: String -> user uid
     * @return Int
     */
    suspend fun eventCount(uid: String) : Int {
        return firestoreAPI.eventCount(uid)
    }

    /**
     * Asks Firestore Service to get the number of sold tickets and
     * tickets marked as assisted from events of the user
     *
     * @param uid: String -> user uid
     * @return Pair<Int, Int>
     */
    suspend fun ventasCount(uid: String) : Pair<Int, Int> {
        return firestoreAPI.ventasCount(uid)
    }

    /**
     * Asks Firestore Service to get the average rating from all the events
     * the user has created
     *
     * @param uid: String -> user uid
     * @return Float
     */
    suspend fun getRating(uid: String) : Float {
        return firestoreAPI.getRating(uid)
    }

    /**
     * Asks Firestore Service to get the total revenue the user has obtained
     * from all of his events
     *
     * @param uid: String -> user uid
     * @return Int
     */
    suspend fun getRevenue(uid: String) : Int {
        return firestoreAPI.getRevenue(uid)
    }

    /**
     * Asks Firestore Service to update the ticket value
     *
     * @param resulted: String -> Qr hash content
     * @return Boolean -> Boolean value, true if the field database was updated, false if
     * it was not
     */
    suspend fun updateTicketValue(resulted: String) : Boolean {
        return firestoreAPI.updateTicketValue(resulted)
    }
    /**
     * Asks FireStore Service to get TicketTypes for view Dropdown.
     *
     * @param idEvento Event Id to get TicketTypes from.
     *
     * @return List -> List containing Triples, each containing TicketType Data. Name, Price, and ID.
     */
    suspend fun getTicketDropdown(idEvento: String) : List<Triple<String, Int, String>> {
        return firestoreAPI.getTicketDropDown(idEvento)
    }
    /**
     * Asks FireStore Service to get all tickets registered to a given Function in the given event of a each type.
     *
     * @param idEvento Event Id to get TicketTypes from.
     *
     * @param idFuncion -> Function to take count from.
     *
     * @return List -> List containing Triples, each containing ticket count of each ticketType.
     * EX: ID, TicketCountOfType, Maximum amount of Tickets specific Type can Have.
     */
    suspend fun getCurrentTicketsFun(idEvento: String, idFuncion: String) : List<Triple<String, Int, Int>> {
        return firestoreAPI.currentTicketsFun(idEvento, idFuncion)
    }
    /**
     * Asks Firestore Service to Store a new Registry of A TicketSale.
     * @param idFuncion -> FunctionID to Register Sale to.
     * @param id_Metodo_Pago -> Payment Type Id.
     *@param id_Tipo_Boleto -> Ticket Type to Register.
     */
    suspend fun registerSale(idFuncion: String,id_Metodo_Pago: String,id_Tipo_Boleto: String){
        return firestoreAPI.RegisterSale(idFuncion,id_Metodo_Pago,id_Tipo_Boleto)
    }
    /**
     * Asks FireStore Service to get UID of PaymentType.
     *
     * @param metodoPago Event Id to get TicketTypes from.
     *
     * @return QuerySnapshot -> QuerySnapshot containing the ID of the PaymentType from name.
     */
    suspend fun getMetodoPagoUid(metodoPago: String) : QuerySnapshot {
        return  firestoreAPI.getMetodoPagoId(metodoPago)
    }

    /**
     * Asks Firestore Service to get the name of the event
     *
     * @param eid: String -> event uid
     * @return String
     */
    suspend fun getEventName(eid: String) : String{
        return firestoreAPI.getEventName(eid)
    }

    /**
     * Asks Firestore Service to get the profits of the event
     *
     * @param eid: String -> event uid
     * @return Int
     */
    suspend fun generalProfitsEvent(eid: String) : Int{
        return firestoreAPI.generalProfitsEvent(eid)
    }

    /**
     * Asks Firestore Service to get the payment methods and number
     * of sales by payment method of the event
     *
     * @param eid: String -> event uid
     * @return MutableMap<String, Int?>
     */
    suspend fun getTicketsbyPM(eid: String) : MutableMap<String, Int?>{
        return firestoreAPI.getTicketsbyPM(eid)
    }

    /**
     * Asks Firestore Service to add a report of a failure
     *
     * @param title: String -> the report's tittle
     * @param description -> the failure's description
     */
    suspend fun addFailure(title: String, description: String) {
        firestoreAPI.addFailure(title, description)
    }

    suspend fun getState(hash_Qr:String): Boolean{
        return firestoreAPI.getState(hash_Qr)
    }
    /**
     * Asks Firestore Service to add a rate of an event in Firestore
     *
     * @param idUser: String -> the User's id
     * @param idEvent: String -> the Event's id
     */
    suspend fun addRating(idUser: String, idEvent : String, rate : Float) {
        firestoreAPI.addRating(idUser, idEvent, rate)
    }
    /**
     * Asks Firestore Service to get a rate from Firebase and verify existance
     *
     * @param idUser: String -> the User's id
     * @param idEvento: String -> the Event's id
     * @return Boolean -> the result from verify the existance
     */
    suspend fun verifyRatingExistence(idUser: String, idEvent: String) : Boolean {
        return firestoreAPI.verifyRatingExistence(idUser, idEvent)
    }

    /**
     * Asks Firestore Service to get the number of tickets marked as assisted
     * from each existing types of tickets as well as the number of
     * sold tickets of each type
     *
     * @param eid: String -> event uid
     * @return MutableMap<String, Pair<Int?, Int?>>
     */
    suspend fun getTicketTypeSA(eid: String): MutableMap<String, Pair<Int?, Int?>> {
        return firestoreAPI.getTicketTypeSA(eid)
    }

    /**
     * Asks Firestore Service to get the number of times an event has received a rating
     * from the range of 0-5 stars as well as the general rating of the event
     *
     * @param eid: String -> event uid
     * @return MutableList<Float>
     */
    suspend fun getRatingByEvent(eid: String): MutableList<Float> {
        return firestoreAPI.getRatingByEvent(eid)
    }

    /**
     * Asks Firestore Service to add a comment in Firestore
     *
     * @param idUser: String -> the User's id
     * @param idEvent: String -> the Event's id
     * @param comment: String -> user´s comment
     * @return Boolean -> the result from adding comment
     */
    suspend fun addComment(idUser: String, idEvent: String, comment: String) {
        return firestoreAPI.addComment(idUser,idEvent,comment)
    }

    /**
     * Asks Firestore Service to get a comment from Firebase and verify existance
     *
     * @param idUser: String -> the User's id
     * @param idEvent: String -> the Event's id
     * @return Boolean -> the result from verifying the existance
     */
    suspend fun verifyCommentExistence(idUser: String, idEvent: String) : Boolean {
        return firestoreAPI.verifyCommentExistence(idUser, idEvent)
    }

    /**
     * Asks Firestore Service to get comments from an event from Firestore service
     *
     * @param idEvento: String -> the Event's id
     * @return QuerySnapshot -> query result from Firestore
     */
    suspend fun getComments(idEvento: String) : QuerySnapshot {
        return firestoreAPI.getComments(idEvento)
    }

    /**
     * Asks Firestore Service to get the number of ticket sales and the number of
     * actual assists to the event
     *
     * @param eid: String -> event uid
     * @return MutableList<Float>
     */
    suspend fun getEventTicketsSA(eid: String) : Pair<Int,Int> {
        return firestoreAPI.getEventTicketsSA(eid)
    }

    suspend fun getEventsActualMonth(eD:Int,eM:Int,eY:Int) : MutableList<EventsInMonth> {
        return firestoreAPI.getEventsActualMonth(eD,eM,eY)
    }

    suspend fun getEvents() : MutableList<EventModel> {
        return firestoreAPI.getEvents()
    }

    suspend fun getEventsUserOrg(uid:String) : MutableList<EventModel> {
        return firestoreAPI.getEventsUserOrg(uid)
    }

    /**
     * Asks Firestore Service to verify if the scanned ticket exists
     *
     * @param result: String -> Qr hash content
     * @return Boolean -> Boolean value, true if the ticket exists, false if it does not
     */
    suspend fun verifyTicketExistence(result: String) : Boolean {
        return firestoreAPI.verifyTicketExistence(result)
    }

    /**
     * Asks Firestore Service to get the profits by payment method of
     * the event
     *
     * @param eid: String -> event uid
     * @return MutableMap<String, Int?>
     */
    suspend fun getRevenuebyPM(eid: String) : MutableMap<String, Int?> {
        return firestoreAPI.getRevenuebyPM(eid)
    }

//    suspend fun getEvents() = firestoreAPI.getEvents()
    suspend fun getCategories() = firestoreAPI.getCategories()
    suspend fun getIdsOfEventosWithidCategoria(idCategoria: String) = firestoreAPI.getIdsOfEventosWithidCategoria(idCategoria)
    suspend fun getCategoryIdByName(name: String) = firestoreAPI.getCategoryIdByName(name)

    // Local database

    val actividadDao = LocalDatabase.getInstance(context).actividadDao
    val estatusDao = LocalDatabase.getInstance(context).estatusDao
    val costoDao = LocalDatabase.getInstance(context).costoDao
    val proyectoDao = LocalDatabase.getInstance(context).proyectoDao
    val usuarioDao = LocalDatabase.getInstance(context).usuarioDao


    suspend fun insertActividad(actividad: Actividad) = actividadDao.insert(actividad)

    suspend fun getAllActividades() = actividadDao.getAll()
    suspend fun getActividadById(id: Int) = actividadDao.getById(id)
    suspend fun getAllActividadById(id: Int) = actividadDao.getAllActivityId(id)
    suspend fun deleteActividad(actividad: Actividad) = actividadDao.delete(actividad)
    fun countPendingActivities(id_a: Int, stringStatus: String): Int = runBlocking {
        val count = async {
            actividadDao.countPendingActivities(id_a, stringStatus)
        }
        count.start()
        count.await()
    }

    fun countDoneActivities(id_a: Int, stringStatus: String): Int = runBlocking {
        val count = async {
            actividadDao.countDoneActivities(id_a, stringStatus)
        }
        count.start()
        count.await()
    }

    fun countAllActivities(id_a: Int): Int = runBlocking {
        val count = async {
            actividadDao.countAllActivities(id_a)
        }
        count.start()
        count.await()
    }
    //Filter Activities
    suspend fun filterActivitiesByStatus(idProyecto:Int, stringStatus:String) = actividadDao.FilterActivityByStatus(idProyecto, stringStatus)
    suspend fun filterActivitiesByArea(idProyecto:Int, stringStatus:String) = actividadDao.FilterActivityByArea(idProyecto, stringStatus)
    suspend fun filterActivitiesByPriority(idProyecto:Int, stringStatus:String) = actividadDao.FilterActivityByPriority(idProyecto, stringStatus)
    suspend fun updateActividad(nombre:String, estatus:String, area:String, prioridad:String, id: Int) = actividadDao.update(nombre, estatus, area, prioridad, id)


    suspend fun insertEstatus(estatus: Estatus) = estatusDao.insert(estatus)




    suspend fun insertProyecto(proyecto: Proyecto) = proyectoDao.insert(proyecto)
    suspend fun getAllProyectos() = proyectoDao.getAll()
    suspend fun getProyectoById(id: Int) = proyectoDao.getById(id)
    suspend fun deleteProyecto(proyecto: Proyecto) = proyectoDao.delete(proyecto)
    suspend fun updateProyecto(proyecto: Proyecto) = proyectoDao.update(proyecto)
    suspend fun updatePresupuesto(presupuestoN: Double, id: Int) =
        proyectoDao.updatePresupuesto(presupuestoN, id)

    suspend fun updateMeta(metaN: Double, id: Int) = proyectoDao.updateMeta(metaN, id)
    suspend fun updateEstatusCompletado(estatusN: Boolean, id: Int) = proyectoDao.updateEstatusCompletado(estatusN, id)
    suspend fun filterProjectsByStatus(stringStatus:Boolean) = proyectoDao.FilterProjectsByStatus(stringStatus)
    suspend fun updateModifyProyect(name: String, date: String, time: String, id: Int) =
        proyectoDao.updateModify(name, date, time, id)


    suspend fun insertCosto(costo: Costo) = costoDao.insert(costo)
    suspend fun getAllCostos(id: Int) = costoDao.getAll(id)
    suspend fun getCostoById(id: Int) = costoDao.getById(id)
    suspend fun deleteCosto(costo: Costo) = costoDao.delete(costo)
    suspend fun updateCosto(costo: Costo) = costoDao.update(costo)


    suspend fun getUserTickets(uid: String): MutableList<GetTicketModel> {
        Log.d("LOG Repositorio", firestoreAPI.getUserTickets(uid).toString())
        return firestoreAPI.getUserTickets(uid)
    }


    suspend fun addUserLocalDB(user: Usuario) = usuarioDao.insertUserLocalDB(user)
    suspend fun getUserLocalDB(userUid: String) : Usuario = usuarioDao.getUserLocalDB(userUid)



    //CreateEvent



    suspend fun addEvent2(event: CreateEventModel, artista: String, funcion: FunctionModel, userUid: String, boletos: EventoTipoBoletoModel, cid: String) {
        firestoreAPI.addEvent2(event, artista, funcion, userUid, boletos, cid)
    }
    suspend fun addFunction(eid: String, fechaFuncion: String, HoraInicio:String, HoraFin:String){
        firestoreAPI.addFunction(eid,fechaFuncion,HoraInicio,HoraFin)
    }

    suspend fun addEventoCategoria(eid: String, idCategoria: String){
        firestoreAPI.addEventoCategoria(eid, idCategoria)
    }

    suspend fun AddEventoTipoBoleto(eid: String, tipoboleto: String, precio: Int, cantidad: Int){
        firestoreAPI.addEventoTipoBoleto(eid, tipoboleto, precio, cantidad)
    }

    suspend fun addArtista(eid: String, nombre_artista: String){
        firestoreAPI.addArtista(eid, nombre_artista)
    }

    suspend fun getCategoriaEvento():List<String>{
        return firestoreAPI.getEventCategory()
    }

    suspend fun getCategoriaEventoFilter(eid:String):List<String>{
        return firestoreAPI.getEventCategoryFilter(eid)
    }

    suspend fun getEventoTipoBoletoFiltered(eid:String):List<String>{
        return firestoreAPI.getEventoTipoBoletoFiltered(eid)
    }

    //Obtener eventos organizador

    suspend fun getOrganizerEvent(uid: String): MutableList<EventModel01> {
        return firestoreAPI.getOrganizerEvents(uid)
    }

    suspend fun getFunctionOrganizer(eid: String): MutableList<FuncionModel> {
        return firestoreAPI.getFunctionOrganizador(eid)
    }


}
