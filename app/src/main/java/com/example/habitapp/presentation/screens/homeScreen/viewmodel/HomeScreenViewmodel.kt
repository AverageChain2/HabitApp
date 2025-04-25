import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitapp.data.habit.Habit
import com.example.habitapp.data.repository.AuthRepo
import com.example.habitapp.data.util.DatabaseResult
import com.example.habitapp.data.util.DatabaseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewmodel(private val authRepo: AuthRepo,
                          private val habitRepo: HabitRepo) : ViewModel() {
    private val _userState = MutableStateFlow(DatabaseState<Habit>())
    //Monitored by component for recomposition on change
    val userState: StateFlow<DatabaseState<Habit>> =
        _userState.asStateFlow()
    var selectedHabit: Habit? = null
    init {
        getListOfHabits(authRepo.currentUser!!.uid)
    }
    private fun getListOfHabits(userAuthId: String) =
        viewModelScope.launch {
            habitRepo.getAll(userAuthId).collect { result ->
                when(result) {
                    is DatabaseResult.Loading -> {
                        _userState.update { it.copy(isLoading = true) }
                    }
                    is DatabaseResult.Success -> {
                        _userState.update { it.copy(data = result.data,
                            isLoading = false ) }
                    }
                    is DatabaseResult.Error -> {
                        _userState.update {
                            it.copy(errorMessage = result.exception.message!!)
                        }
                    }
                }
            }
        }
    fun habitHasBeenSelected(): Boolean = selectedHabit != null
    fun deleteHabit(){
        if (habitHasBeenSelected()) {
            habitRepo.delete(selectedHabit!!, authRepo.currentUser!!.uid)
            selectedHabit = null
        }
    }
}