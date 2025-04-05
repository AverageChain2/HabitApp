//package com.example.habitapp.presentation.screens.Register
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.habitapp.data.room.SortType
//import com.example.habitapp.data.room.UserEvent
//import com.example.habitapp.data.room.UserState
//import com.example.habitapp.data.room.database.user.User
//import com.example.habitapp.data.room.database.user.UserDao
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.SharingStarted
//import kotlinx.coroutines.flow.combine
//import kotlinx.coroutines.flow.stateIn
//import kotlinx.coroutines.flow.update
//import kotlinx.coroutines.launch
//
//class UserViewModel(private val dao: UserDao) : ViewModel() {
//
//    private val _sortType = MutableStateFlow(SortType.FIRST_NAME)
//    private val _users = dao.getUsers()
//    private val _state = MutableStateFlow(UserState())
//
//    val state = combine(_state, _users) {
//        state, users ->
//        state.copy(
//            users = users
//        )
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserState())
//
//    fun onEvent(event: UserEvent) {
//        when (event) {
//            is UserEvent.DeleteUser -> {
//                viewModelScope.launch {
//                    dao.deleteUser(event.user)
//                }
//            }
//            UserEvent.HideDialog -> {
//                _state.update { it.copy(
//                    isAddingUser = false
//                ) }
//            }
//            UserEvent.SaveUser -> {
//                val firstName = state.value.firstName
//                val surname = state.value.surname
//                val telNo = state.value.telNo
//
//                if(firstName.isBlank() || surname.isBlank() || telNo.isBlank()) {
//                    return
//                }
//                val user = User(
//                    firstName = firstName,
//                    surname =  surname,
//                    telNo = telNo
//                )
//                viewModelScope.launch {
//                    dao.insertUser(user)
//                }
//                _state.update {
//                    it.copy(
//                        isAddingUser = false,
//                        firstName = "",
//                        surname = "",
//                        telNo = ""
//                    )
//                }
//            }
//            is UserEvent.SetFirstName -> {
//                _state.update { it.copy(
//                    firstName = event.firstName
//                ) }
//            }
//            is UserEvent.SetSurname -> {
//                _state.update { it.copy(
//                    surname = event.surname
//                ) }
//            }
//            is UserEvent.SetTelNo -> {
//                _state.update { it.copy(
//                    telNo = event.telNo
//                ) }
//            }
//            UserEvent.ShowDialog -> {
//                _state.update { it.copy(
//                    isAddingUser = true
//                ) }
//            }
//            is UserEvent.SortUser -> {
//                _sortType.value = event.sortType
//            }
//        }
//    }
//}