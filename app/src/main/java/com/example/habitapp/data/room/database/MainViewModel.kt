import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitapp.data.room.database.AppDatabase
import com.example.habitapp.data.room.database.group.Group
import com.example.habitapp.data.room.database.habit.Habit
import com.example.habitapp.data.room.database.user.User
import kotlinx.coroutines.launch
import java.util.UUID
import java.time.LocalDate
import android.util.Log


class MainViewModel(private val db: AppDatabase) : ViewModel() {

    fun performDatabaseOperations() {
        val userDao = db.userDao()
        val groupDao = db.groupDao()
        val habitDao = db.habitDao()

        viewModelScope.launch {
            val user = User(id = UUID.randomUUID(), email = "john.doe@example.com")
            userDao.insertUser(user)
            val group = Group(id =1, groupName = "First group")
            groupDao.upsertGroup(group)
            val habit = Habit(
                id = 1,
                unit = "Coffee",
                goal = 1,
                timeframe = 1,
                userId = user.id,
                groupId = 1,
                timeStamp = LocalDate.now()
            )


            habitDao.insertHabit(habit)

            Log.println(Log.ASSERT,"MainViewModel", "created all objects")

            val habits = habitDao.getHabitsForUser(user.id)
            println("User Habits: $habits")
        }
    }
}