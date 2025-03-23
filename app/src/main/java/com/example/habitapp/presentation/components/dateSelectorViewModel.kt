package com.example.habitapp.presentation.components

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDate
import java.util.Date

class DateSelectorViewModel : ViewModel() {


    private val _selectedDate = MutableLiveData(LocalDate.now())
    val selectedDate: LiveData<LocalDate> = _selectedDate

    fun onDateChange(newDate: LocalDate) {
        _selectedDate.value = newDate
    }
}