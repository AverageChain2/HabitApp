import com.example.habitapp.data.habit.Habit
import com.example.habitapp.data.habit.HabitDAO
import com.example.habitapp.data.util.DatabaseResult
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.Flow

interface HabitRepo{
    fun delete(habit: Habit, userUUID: String): Task<Void>
    fun add(habit: Habit, userUUID: String)
    fun edit(habit: Habit, userUUID: String)
    suspend fun getAll(userUUID: String):
            Flow<DatabaseResult<List<Habit?>>>
}
class HabitRepository(private val habitDAO: HabitDAO) : HabitRepo {
    override fun delete(habit: Habit, userUUID: String) =
        habitDAO.delete(habit, userUUID)
    override fun add(habit: Habit, userUUID: String) {
        habitDAO.insert(habit, userUUID)}
    override fun edit(habit: Habit, userUUID: String) {
        habitDAO.update(habit, userUUID)}
    override suspend fun getAll(userUUID: String):
            Flow<DatabaseResult<List<Habit?>>> {
        return habitDAO.getHabits(userUUID)
    }
}