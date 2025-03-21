package com.example.habitapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitapp.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val userRepository = UserRepository()
    private val _userText = MutableStateFlow("Loading...") // StateFlow for the UI
    val userText: StateFlow<String> get() = _userText

    fun fetchUserRecord(id: String) {
        viewModelScope.launch {
            userRepository.getRecord(id) { result ->
                _userText.value = result.toString() // Update state when data is fetched
            }
        }
    }
}