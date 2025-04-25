import com.example.habitapp.data.room.database.habit.Habit
import com.example.habitapp.data.room.database.habit.HabitDao
import kotlinx.coroutines.flow.Flow

interface Repository<T> {
    suspend fun delete(habit: T)
    suspend fun insert(habit: T)
    suspend fun update(habit: T)
//    suspend fun deleteAll()
    fun getAll(): Flow<List<T>>
//    fun getById(id: Int): Flow<T>
//    fun getByString(firstName: String): Flow<T>
}
class HabitRepository(private val habitDAO: HabitDao) : Repository<Habit> {
    override suspend fun delete(habit: Habit) = habitDAO.deleteHabit(habit)
//    override suspend fun deleteAll() = habitDAO.deleteAll()
    override suspend fun update(habit: Habit) = habitDAO.update(habit)
    override suspend fun insert(habit: Habit)= habitDAO.insertHabit(habit)
    override fun getAll(): Flow<List<Habit>> = habitDAO.getHabits()
//    override fun getById(id: Int): Flow<Habit> = habitDAO.findById(id)
//    override fun getByString(firstName: String): Flow<Habit> =
//        habitDAO.findByFirstName(firstName)
}