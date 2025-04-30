import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitapp.data.habit.Habit
import com.example.habitapp.data.repository.AuthRepo
import com.example.habitapp.data.util.DatabaseResult
import com.example.habitapp.data.util.DatabaseState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate

class HomeScreenViewmodel(
    private val authRepo: AuthRepo,
    private val habitRepo: HabitRepo
) : ViewModel() {

    private val _userState = MutableStateFlow(DatabaseState<Habit>())
    val userState: StateFlow<DatabaseState<Habit>> = _userState.asStateFlow()

    private val _userGroups = MutableStateFlow<List<String?>>(emptyList())
    val userGroups: StateFlow<List<String?>> = _userGroups.asStateFlow()

    private val _selectedGroup = MutableStateFlow<String?>(null)
    val selectedGroup: StateFlow<String?> = _selectedGroup.asStateFlow()

    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> = _selectedDate.asStateFlow()

    var selectedHabit: Habit? = null

    init {
        authRepo.currentUser?.uid?.let { userId ->
            getGroups(userId)
        }

        viewModelScope.launch {
            userState.collect { updatedState ->
                Log.d("HomeScreenViewModel", "User state updated: ${updatedState.data}")
            }
        }
    }


    fun onGroupChange(newGroup: String) {
        _selectedGroup.value = newGroup
        getHabitsInGroupOnDate(
            authRepo.currentUser?.uid ?: return,
            newGroup,
            selectedDate.value.toString()
        )
    }

    fun onDateChange(newDate: LocalDate) {
        _selectedDate.value = newDate
        getHabitsInGroupOnDate(
            authRepo.currentUser?.uid ?: return,
            selectedGroup.value ?: return,
            newDate.toString()
        )
    }

    private fun getGroups(userAuthId: String) = viewModelScope.launch {
        habitRepo.getAllGroupNames(userAuthId).collect { result ->
            when (result) {
                is DatabaseResult.Success -> {
                    _userGroups.update { result.data }
                    result.data.firstOrNull()?.let { onGroupChange(it) } // Set first group as default
                    Log.d("HomeScreenViewModel", "Groups retrieved: $selectedGroup")
                }
                is DatabaseResult.Error -> _userGroups.update { emptyList() }
                DatabaseResult.Loading -> _userState.update { it.copy(isLoading = true) }
            }
        }
    }

    fun getHabitsInGroupOnDate(userAuthUUID: String, groupName: String, date: String) =
        viewModelScope.launch {
            habitRepo.getHabitsInGroupOnDate(userAuthUUID, groupName, date).collect { result ->
                when (result) {
                    is DatabaseResult.Loading -> _userState.update { it.copy(isLoading = true) }
                    is DatabaseResult.Success -> {
                        _userState.update { it.copy(data = result.data, isLoading = false) }
                        Log.d("HomeScreenViewModel", "Habits retrieved: ${result.data} ${selectedDate.value} ${selectedGroup.value}")
                    }
                    is DatabaseResult.Error -> _userState.update {
                        it.copy(errorMessage = result.exception.message ?: "Unknown error")
                    }
                }
            }
        }

    fun calculateOverallProgress(): Float {
        val habits = userState.value.data.filterNotNull() // Exclude null values
        return if (habits.isNotEmpty()) {
            habits.map { it.progress }.sum().div(habits.map {it.goal}.sum().toFloat()) // Compute average progress
        } else {
            0f  // Default value when no habits exist
        }
    }
    fun updateHabitToMaxProgress(selectHabit: Habit){
        Log.d("HomeScreenViewModel", "Updating habit retrieved: ${selectHabit.id} ${selectedDate.value} ${selectedGroup.value}")
        if (selectHabit!!.progress == selectHabit.goal) {
            selectHabit!!.progress = 0
        } else {
            selectHabit!!.progress = selectHabit.goal
        }
            habitRepo.edit(selectHabit!!, authRepo.currentUser!!.uid,
                selectedGroup.value.toString(), selectedDate.value.toString()
            )

    }



    fun habitHasBeenSelected(): Boolean = selectedHabit != null

    fun deleteHabit() {
        selectedHabit?.let {
            habitRepo.delete(it, authRepo.currentUser?.uid ?: return)
            selectedHabit = null
        }
    }
}