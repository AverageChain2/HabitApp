import com.example.habitapp.data.habit.Habit
import com.example.habitapp.data.habit.HabitDAO
import com.example.habitapp.data.util.DatabaseResult
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface HabitRepo{
    fun delete(habit: Habit, userUUID: String): Task<Void>
    fun add(habit: Habit, group: String, userUUID: String)
    fun edit(habit: Habit, userUUID: String)
//    suspend fun getAll(userUUID: String):
//            Flow<DatabaseResult<List<Habit?>>>
    suspend fun getHabitsInGroupOnDate(userUUID: String, group: String, date: String):
            Flow<DatabaseResult<List<Habit?>>>
    suspend fun getSingleHabit(userUUID: String, habit: Habit) :
            Flow<DatabaseResult<Habit?>>
    suspend fun getAllGroupNames(userUUID: String):
            Flow<DatabaseResult<List<String?>>>
}
class HabitRepository(private val habitDAO: HabitDAO) : HabitRepo {
    override fun delete(habit: Habit, userUUID: String) =
        habitDAO.delete(habit, userUUID)
    override fun add(habit: Habit, group: String, userUUID: String) {
        habitDAO.insert(habit, group, userUUID)}

    override fun edit(habit: Habit, userUUID: String) {
        habitDAO.update(habit, userUUID)}
//    override suspend fun getAll(userUUID: String):
//            Flow<DatabaseResult<List<Habit?>>> {
//        return habitDAO.getHabits(userUUID)
//    }

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