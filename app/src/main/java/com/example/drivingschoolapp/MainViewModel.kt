package com.example.drivingschoolapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drivingschoolapp.ROOM.UserEntity
import com.example.drivingschoolapp.ROOM.UserRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _currentUser = MutableStateFlow<UserEntity?>(null)
    val currentUser: StateFlow<UserEntity?> = _currentUser.asStateFlow()

    init {
        loadCurrentUser()
    }

    private fun loadCurrentUser() {
        viewModelScope.launch {
            userRepository.getCurrentUser().collect { users ->
                _currentUser.value = users.firstOrNull()
            }
        }
    }

    fun insertUser(user: UserEntity) {
        viewModelScope.launch {
            userRepository.insert(user)
            loadCurrentUser()
        }
    }

    fun updateUser(user: UserEntity) {
        viewModelScope.launch {
            userRepository.update(user)
            loadCurrentUser()
        }
    }

}



