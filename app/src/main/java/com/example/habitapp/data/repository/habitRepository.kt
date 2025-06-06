import android.util.Log
import androidx.annotation.OptIn
//import androidx.media3.common.util.Log
//import androidx.media3.common.util.UnstableApi
import com.example.habitapp.data.habit.Habit
import com.example.habitapp.data.habit.HabitDAO
import com.example.habitapp.data.util.DatabaseResult
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.time.LocalDate

interface HabitRepo{
    fun delete(habit: Habit, userUUID: String)
    fun deleteAllWithID(habit: Habit, userUUID: String)
    fun add(habit: Habit, group: String, userUUID: String)
    fun edit(habit: Habit, userUUID: String)
    suspend fun batchUpdateGroup(habit: Habit, userUUID: String, originalGroup: String)

    suspend fun getHabitsInGroupOnDate(userUUID: String, group: String, date: String):
            Flow<DatabaseResult<List<Habit?>>>
    suspend fun getSingleHabit(userUUID: String, habit: Habit) :
            Flow<DatabaseResult<Habit?>>
    suspend fun getAllGroupNames(userUUID: String):
            Flow<DatabaseResult<List<String?>>>
}
class HabitRepository(private val habitDAO: HabitDAO) : HabitRepo {
    override fun delete(habit: Habit, userUUID: String) {
        habitDAO.delete(habit, userUUID)}
    override fun deleteAllWithID(habit: Habit, userUUID: String) {
        habitDAO.deleteAllWithID(habit, userUUID)}
    override fun add(habit: Habit, group: String, userUUID: String) {
        habitDAO.insert(habit, group, userUUID)}

    override fun edit(habit: Habit, userUUID: String) {
        Log.d("habitRepository", "editing habit ${habit.id}")
        habitDAO.update(habit, userUUID)
    }

    override suspend fun batchUpdateGroup(habit: Habit, userUUID: String, originalGroup: String) {
        Log.d("HabitRepository", "Editing habit ${habit.id}")

        // Run update asynchronously on the IO dispatcher

            habitDAO.batchUpdateGroup(habit, userUUID, originalGroup)

    }




    override suspend fun getHabitsInGroupOnDate(
        userUUID: String,
        group: String,
        date: String
    ): Flow<DatabaseResult<List<Habit?>>> {
        return habitDAO.getHabitsInGroupOnDate(userUUID, group, date)
    }
    override suspend fun getSingleHabit(userUUID: String, habit: Habit):
            Flow<DatabaseResult<Habit?>> {
        return habitDAO.getSingleHabit(userUUID, habit)
    }

    override suspend fun getAllGroupNames(userUUID: String):
            Flow<DatabaseResult<List<String>>> {
        return habitDAO.getGroups(userUUID)
    }
}
