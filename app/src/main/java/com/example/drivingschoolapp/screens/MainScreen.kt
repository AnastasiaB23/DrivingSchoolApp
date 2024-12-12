package com.example.drivingschoolapp.screens

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.drivingschoolapp.MainViewModel
import com.example.drivingschoolapp.bottom_navigation.BottomItem
import com.example.drivingschoolapp.bottom_navigation.BottomNavigation
import com.example.drivingschoolapp.ui.theme.light_yellow_main

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(mainViewModel: MainViewModel) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = light_yellow_main),
                title = { Text(text = "Твоя автошкола") }
            )
        },
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = "ScheduleScreen"
        ) {
            composable(BottomItem.ScheduleScreen.route) {
                ScheduleScreen()
            }
            composable(BottomItem.ExamsScreen.route) {
                ExamsScreen()
            }

            composable(BottomItem.ProfileScreen.route) {
                ProfileScreen(mainViewModel = mainViewModel)
            }
            composable(BottomItem.AboutSchoolScreen.route) {
                AboutSchoolScreen()
            }
        }
    }
}