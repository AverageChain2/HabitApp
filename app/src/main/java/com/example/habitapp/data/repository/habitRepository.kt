package com.example.habitapp.data.repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.habitapp.data.model.User
import java.util.UUID

private const val ROOT_FOLDER = "users"

class HabitRepository {
    private val firebase: FirebaseDatabase = FirebaseDatabase.getInstance("https://habitapp-4a07f-default-rtdb.europe-west1.firebasedatabase.app/")
    private val database: DatabaseReference = firebase.getReference(ROOT_FOLDER)

    fun saveRecord(firstName: String, surname: String, telNo: String) {
        val user = User(firstName, surname, telNo)
        database.child(UUID.randomUUID().toString()).setValue(user)
    }
    fun getRecord(id: String, onResult: (User?) -> Unit) {
        database.child(id).get()
            .addOnSuccessListener { snapshot ->
                val user = snapshot.getValue(User::class.java)
                onResult(user) // Pass the user or null if not found
            }
            .addOnFailureListener {
                onResult(null) // Handle failure by passing null
            }
    }

}