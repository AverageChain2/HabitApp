import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitapp.data.room.database.habit.Habit
import com.example.habitapp.data.room.database.habit.HabitDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeScreenViewmodel(private val repo: Repository<Habit>) : ViewModel() {
    val items: Flow<List<Habit>> = repo.getAll()
    var selectedHabit: Habit?= null
    fun habitHasBeenSelected(): Boolean = selectedHabit!=null
    suspend fun deleteHabit(){
        if (habitHasBeenSelected()) {
            repo.delete(selectedHabit!!)
            selectedHabit = null
        }
    }
}