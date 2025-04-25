package com.example.habitapp.data.repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.habitapp.data.model.Habit
import java.time.LocalDate
import java.util.UUID

private const val ROOT_FOLDER = "habits"

class HabitRepository {
    private val firebase: FirebaseDatabase = FirebaseDatabase.getInstance("https://habitapp-4a07f-default-rtdb.europe-west1.firebasedatabase.app/")
    private val database: DatabaseReference = firebase.getReference(ROOT_FOLDER)

    fun saveRecord(id: UUID, unit: String, goal: Int, progress: Int, timeFrame: Int, group: String, timeStamp: LocalDate) {
        val habit = Habit(id, unit, goal, progress, timeFrame, group, timeStamp)
        database.child(UUID.randomUUID().toString()).setValue(habit)
    }
    fun getRecord(id: String, onResult: (Habit?) -> Unit) {
        database.child(id).get()
            .addOnSuccessListener { snapshot ->
                val habit = snapshot.getValue(Habit::class.java)
                onResult(habit) // Pass the habit or null if not found
            }
            .addOnFailureListener {
                onResult(null) // Handle failure by passing null
            }
    }

}