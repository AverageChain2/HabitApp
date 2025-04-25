//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.habitapp.data.room.database.AppDatabase
//import com.example.habitapp.data.room.database.habit.Habit
//import kotlinx.coroutines.launch
//import java.util.UUID
//import java.time.LocalDate
//import android.util.Log
//
//
//class MainViewModel(private val db: AppDatabase) : ViewModel() {
//
//    fun performDatabaseOperations() {
//        val habitDao = db.habitDao()
//
//        viewModelScope.launch {
//
//            val habit = Habit(
//                id = UUID.randomUUID(),
//                unit = "Coffee",
//                goal = 1,
//                timeframe = 1,
//                group = "drink",
//                timeStamp = LocalDate.now()
//            )
//
//
//            habitDao.insertHabit(habit)
//
//            Log.println(Log.ASSERT,"MainViewModel", "created all objects")
//
//            val habits = habitDao.getHabitsForUser()
//            println("User Habits: $habits")
//        }
//    }
//}