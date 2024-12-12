package com.example.drivingschoolapp.bottom_navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.drivingschoolapp.ui.theme.grey_1
import com.example.drivingschoolapp.ui.theme.light_orange_main

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomItem.ScheduleScreen,
        BottomItem.ExamsScreen,
        BottomItem.ProfileScreen,
        BottomItem.AboutSchoolScreen
    )

    NavigationBar(containerColor = light_orange_main) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = item.title
                    )
                },
                label = { Text(text = item.title, fontSize = 10.sp) },
                colors = NavigationBarItemColors(
                    selectedIconColor = Color.Black,
                    selectedTextColor = Color.Black,
                    unselectedIconColor = grey_1,
                    unselectedTextColor = grey_1,
                    disabledIconColor = Color.Gray,
                    disabledTextColor = Color.Gray,
                    selectedIndicatorColor = Color.Transparent
                )
            )
        }
    }
}
