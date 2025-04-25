package com.example.habitapp.data.habit

import com.example.habitapp.data.util.DatabaseResult
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.UUID
class HabitDAO(private val database: DatabaseReference) {
    suspend fun getHabits(userAuthUUID: String):
            Flow<DatabaseResult<List<Habit?>>> = callbackFlow {
        trySend(DatabaseResult.Loading)
        database.child(userAuthUUID).keepSynced(true)
        val event = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val habits = ArrayList<Habit>()
                for (childSnapshot in snapshot.children) {
                    val habit =
                        childSnapshot.getValue(Habit::class.java)
                    habit!!.id = childSnapshot.key.toString()
                    habits.add(habit)
                }
                trySend(DatabaseResult.Success(habits))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(DatabaseResult.Error(Throwable(error.message)))
            }
        }
        database.child(userAuthUUID).addValueEventListener(event)
        awaitClose { close() }
    }

    fun insert(
        newHabit: Habit,
        userAuthUUID: String
    ) =
        database.child(userAuthUUID).child(UUID.randomUUID().toString())
            .setValue(newHabit)

    fun update(editHabit: Habit, userAuthUUID: String) {
        val habitId = editHabit.id.toString() //retrieved for sub folder key
        editHabit.id = null //Clear so not saved inside folder
        database.child(userAuthUUID).child(habitId).setValue(editHabit)
    }

    fun delete(habit: Habit, userAuthUUID: String) =
        database.child(userAuthUUID).child(habit.id.toString()).removeValue()
}