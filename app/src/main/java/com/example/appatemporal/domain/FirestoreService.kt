package com.example.appatemporal.domain

import android.util.Log
import com.example.appatemporal.domain.models.TicketModel
import com.example.appatemporal.domain.models.UserModel
import com.google.common.base.Enums.getField
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirestoreService {
    private val db = Firebase.firestore

    fun addUser(uid: String, user: UserModel) {
        db.collection("Usuario")
            .document(uid)
            .set(user)
            .addOnSuccessListener {
                Log.d("FirestoreLogs","Added User Correctly")
            }
            .addOnFailureListener {
                Log.d("FirestoreLogs","Added user failed, exception: $it")
            }
    }

    suspend fun addUserRole(uid: String, role: String) {
        val dbRole = db.collection("Rol")
            .whereEqualTo("nombre_Rol", role)
            .get()
            .addOnSuccessListener {
                Log.d("FirestoreLogs","Got Role Correctly: ${it.documents[0].id}")
            }.await()

        val userRole = hashMapOf(
            "id_Usuario" to uid,
            "id_Rol" to dbRole.documents[0].id
        )

        db.collection("Usuario_Rol")
            .add(userRole)
            .addOnSuccessListener {
                Log.d("FirestoreLogs","Added User wih Role Correctly")
            }
            .addOnFailureListener {
                Log.d("FirestoreLogs","Added user failed, exception: $it")
            }
    }

    suspend fun verifyUser(uid: String) : Boolean {
        var userExists = false
        db.collection("Usuario")
            .document(uid)
            .get()
            .addOnSuccessListener { documentRef ->
                if (documentRef.exists()) {
                    userExists = true
                }
            }
            .await()
        return userExists
    }

    suspend fun getUser(uid: String) : DocumentSnapshot {
        var userData: DocumentSnapshot =
            db.collection("Usuario")
            .document(uid)
            .get()
            .await()
        return userData
    }

    suspend fun getUserRole(uid: String) : DocumentSnapshot {
        var dbRole: QuerySnapshot =
            db.collection("Usuario_Rol")
                .whereEqualTo("id_Usuario", uid)
                .get()
                .await()
        var userRole: DocumentSnapshot =
            db.collection("Rol")
                .document(dbRole.documents[0].data?.get("id_Rol").toString())
                .get()
                .await()
        return userRole
    }

    // Que evento le corresponde al boleto
    // uid: userId, eid: eventId, fid: funcionId
    // getUserTicket
    /*suspend fun agetUserTickets(uid: String) : MutableList<TicketModel> {

        val ticket_list: MutableList<TicketModel> = arrayListOf()

        val ticket = db.collection("Boleto")
            .whereEqualTo("Id_Usuario", uid)
            .get()
            .await()

        for(documents in ticket){

            val hash_qr = documents.getField<String>("hash_Qr").toString()

            val boleto_funcion = documents.getField<String>("id_Funcion").toString()

            val funcion = db.collection("Funcion")
                .document(boleto_funcion)
                .get()
                .await()

            val fecha = funcion.getField<String>("fechaFuncion").toString()
            val horario = funcion.getField<String>("horarioFuncion").toString()

            val funcion_evento = funcion.getField<String>("id_Evento").toString()

            val evento = db.collection("Evento")
                .document(funcion_evento)
                .get()
                .await()

            val nombre_evento = evento.getField<String>("EventName").toString()
            val lugar = evento.getField<String>("LugarEvento").toString()
            val direccion = evento.getField<String>("DireccionEvento").toString()
            val ciudad = evento.getField<String>("CiudadEvento").toString()
            val estado = evento.getField<String>("EstadoEvento").toString()

            val objeto_ticket = TicketModel(nombre_evento, fecha, horario, lugar, direccion, ciudad, estado, hash_qr)
            ticket_list.add(objeto_ticket)
        }

        Log.d("Lista de objetos", ticket_list.toString())


        return ticket_list
    }*/

    suspend fun getUserTickets(uid : String) : MutableList<TicketModel> {
        var result : MutableList<TicketModel> = arrayListOf()
        var ticket : TicketModel = TicketModel()
        var boletos : QuerySnapshot =
            db.collection("Boleto")
                .whereEqualTo("id_Usuario",uid)
                .get()
                .await()
        for (boleto in boletos){
            var funciones : QuerySnapshot =
                db.collection("Funcion")
                    .whereEqualTo(FieldPath.documentId(),boleto.data?.get("id_Funcion"))
                    .get()
                    .await()
            var evento : QuerySnapshot =
                db.collection("Evento")
                    .whereEqualTo(FieldPath.documentId(),funciones.documents[0].data?.get("id_Evento"))
                    .get()
                    .await()
            ticket.nombre_evento = evento.documents[0].data?.get("nombre_Evento").toString()
            ticket.fecha = funciones.documents[0].data?.get("fecha").toString()
            ticket.horario = funciones.documents[0].data?.get("hora_Inicio").toString()
            ticket.lugar = evento.documents[0].data?.get("nombre_Ubicacion").toString()
            ticket.direccion = evento.documents[0].data?.get("direccion").toString()
            ticket.ciudad = evento.documents[0].data?.get("ciudad").toString()
            ticket.estado = evento.documents[0].data?.get("estado").toString()
            ticket.hash_qr = boleto.data?.get("hash_QR").toString()
            result.add(ticket)
            Log.d("LOG ticket",ticket.toString())
        }
        Log.d("LOG aqui",result.isEmpty().toString())
        return result
    }

    suspend fun getUserFunctions(uid: String) : QuerySnapshot {
        val funcion = db.collection("Usuario_Evento")
            .whereEqualTo("Id_Usuario", uid)
            .get()
            .await()
        return funcion
    }
}