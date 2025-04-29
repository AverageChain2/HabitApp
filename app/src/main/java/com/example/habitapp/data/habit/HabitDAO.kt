package com.example.habitapp.data.habit

import android.util.Log
import com.example.habitapp.data.util.DatabaseResult
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.time.LocalDate
import java.util.UUID
class HabitDAO(private val database: DatabaseReference) {

    fun getHabitsInGroupOnDate(userAuthUUID: String, group: String, date: String):
            Flow<DatabaseResult<List<Habit?>>> = callbackFlow {
        trySend(DatabaseResult.Loading)
        database.child(userAuthUUID).child(group).child(date).keepSynced(true)
        val event = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val habits = ArrayList<Habit>()
                for (childSnapshot in snapshot.children) {
                    Log.d("HabitDAO", "$childSnapshot")
                    val habit =
                        childSnapshot.getValue(Habit::class.java)
                    habit!!.id = childSnapshot.key.toString()
                    Log.d("HabitDAO", "$habit ${habit.id}")
                    habits.add(habit)
                }
                trySend(DatabaseResult.Success(habits))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(DatabaseResult.Error(Throwable(error.message)))
            }
        }
    database.child(userAuthUUID).child(group).child(date).addValueEventListener(event)
    awaitClose { close() }
    }
    fun getGroups(userAuthUUID: String): Flow<DatabaseResult<List<String>>> = callbackFlow {
        trySend(DatabaseResult.Loading)

        val event = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val groups = snapshot.children.mapNotNull { it.key } // Extract group names
                trySend(DatabaseResult.Success(groups))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(DatabaseResult.Error(Throwable(error.message)))
            }
        }

        database.child(userAuthUUID).addListenerForSingleValueEvent(event)
        awaitClose { close() }
    }


    fun insert(
        newHabit: Habit,
        group: String,
        userAuthUUID: String
    ) =
        database.child(userAuthUUID).child(group).child(LocalDate.now().toString()).child(UUID.randomUUID().toString())
            .setValue(newHabit)

    fun update(editHabit: Habit, userAuthUUID: String) {
        val habitId = editHabit.id.toString() // Retrieved for sub-folder key
        editHabit.id = null
        val group = editHabit.group
        editHabit.group = null
        val date = editHabit.date
        editHabit.date = null// Clear so it's not saved inside the folder

        val path = "$userAuthUUID/$group/$date/$habitId"
        Log.d("HabitDAO", "Updating habit at path: $path")

        if (group != null && date != null) {
            database.child(userAuthUUID).child(group).child(date).child(habitId).setValue(editHabit)
        }
    }


    fun delete(habit: Habit, userAuthUUID: String) =
        database.child(userAuthUUID).child(habit.id.toString()).removeValue()
}