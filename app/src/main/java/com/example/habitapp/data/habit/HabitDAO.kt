package com.example.habitapp.data.habit

import android.util.Log
import com.example.habitapp.data.util.DatabaseResult
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
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

    fun getSingleHabit(userAuthUUID: String, habit: Habit): Flow<DatabaseResult<Habit?>> =
        callbackFlow {
            trySend(DatabaseResult.Loading)

            val habitRef = database.child(userAuthUUID).child(habit.group.toString())
                .child(habit.date.toString()).child(habit.id.toString())
            habitRef.keepSynced(true)

            val event = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val habitData = snapshot.getValue(Habit::class.java)
                    if (habitData != null) {
                        habitData.id = habit.id
                        habitData.date = habit.date
                        habitData.group = habit.group
                    }
                    trySend(DatabaseResult.Success(habitData))
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(DatabaseResult.Error(Throwable(error.message)))
                }
            }

            habitRef.addValueEventListener(event)
            awaitClose { habitRef.removeEventListener(event) }
        }


    fun getGroups(userAuthUUID: String): Flow<DatabaseResult<List<String>>> = callbackFlow {
        trySend(DatabaseResult.Loading)

        val event = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val groups = snapshot.children.mapNotNull { it.key }
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
        database.child(userAuthUUID).child(group).child(LocalDate.now().toString())
            .child(UUID.randomUUID().toString())
            .setValue(newHabit)

    fun update(editHabit: Habit, userAuthUUID: String) {
        val habitId = editHabit.id.toString() // Retrieved for sub-folder key
        editHabit.id = null
        val group = editHabit.group
        editHabit.group = null
        val date = editHabit.date
        editHabit.date = null // Clear so it's not saved inside the folder

        val path = "$userAuthUUID/$group/$date/$habitId"
        Log.d("HabitDAO", "Updating habit at path: $path")

        if (group != null && date != null) {
            database.child(userAuthUUID).child(group).child(date).child(habitId).setValue(editHabit)
        }
    }


    suspend fun batchUpdateGroup(habit: Habit, userAuthUUID: String, newGroup: String) {
        var date = LocalDate.now()
        val originalGroup = habit.group ?: return
        val id = habit.id ?: return
        val maxIterations = 20
        var iterations = 0
        var habitResult: DatabaseResult<Habit?>? = null

        while (iterations < maxIterations) {
            Log.d("HabitDAO", "date $date")

            habit.date = date.toString()

            habitResult = null

            habitResult = getSingleHabit(userAuthUUID, habit)
                .filter { it is DatabaseResult.Success || it is DatabaseResult.Error }
                .firstOrNull()

            if (habitResult is DatabaseResult.Success && habitResult.data != null) {
                val currentHabit = habitResult.data ?: return
                val habitToUpdate = currentHabit.copy(

                )
                habitToUpdate.date = date.toString()
                habitToUpdate.id = id
                habitToUpdate.group = newGroup

                update(habitToUpdate, userAuthUUID)
                Log.d("HabitDAO", "Updated habit from $originalGroup to $newGroup on date $date")


                val habitToDelete = currentHabit.copy()
                habitToDelete.date = date.toString()
                habitToDelete.id = id
                habitToDelete.group = originalGroup

                delete(habitToDelete, userAuthUUID)
                Log.d("HabitDAO", "Deleted original habit in $originalGroup on date $date")


            } else {
                Log.e("HabitDAO", "Failed to retrieve habit for date $date")
                break
            }

            date = date.minusDays(1)
            iterations++
            Log.d("HabitDAO", "date $iterations")
        }
        Log.d("HabitDAO", "Batch update completed")
    }


    fun delete(habit: Habit, userAuthUUID: String) {
        val habitRef =
            database.child(userAuthUUID).child(habit.group.toString()).child(habit.date.toString())
                .child(habit.id.toString())

        habitRef.get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                habitRef.removeValue().addOnSuccessListener {
                    Log.d("HabitDAO", "Successfully deleted habit ${habit.id}")
                }
            } else {
                Log.e("HabitDAO", "Habit ${habit.id} not found, skipping deletion.")
            }
        }.addOnFailureListener {
            Log.e("HabitDAO", "Error retrieving habit before deletion: ${it.message}")
        }
    }
}