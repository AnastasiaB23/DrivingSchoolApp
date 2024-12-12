package com.example.drivingschoolapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.drivingschoolapp.ROOM.UserRepository
import com.example.drivingschoolapp.screens.MainScreen
import com.example.drivingschoolapp.ui.login.LoginScreen

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app = application as App
        val userRepository = app.userRepository
        val viewModelFactory = MainViewModelFactory(userRepository)
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        setContent {
            val navController = rememberNavController()
            val viewModel: MainViewModel = viewModel(factory = viewModelFactory)

            NavHost(
                navController = navController,
                startDestination = if (isLoggedIn) "MainScreen" else "LoginScreen"
            ) {
                composable("LoginScreen") {
                    LoginScreen(onNavigateToMainScreen = { navData ->
                        sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
                        navController.navigate("MainScreen") {
                            popUpTo("LoginScreen") { inclusive = true }
                        }
                    })
                }

                composable("MainScreen") {
                    MainScreen(mainViewModel = viewModel)
                }
            }
        }
    }
}

class MainViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}