package com.example.drivingschoolapp.bottom_navigation

import com.example.drivingschoolapp.R

sealed class BottomItem (val title: String, val iconId: Int, val route: String){

    object ScheduleScreen: BottomItem("Расписание", R.drawable.bottom_schedule_icon, "ScheduleScreen")
    object ExamsScreen: BottomItem("Зачеты", R.drawable.bottom_exams_icon, "ExamsScreen")
    object ProfileScreen: BottomItem("Профиль", R.drawable.bottom_chats_icon, "ProfileScreen")
    object AboutSchoolScreen: BottomItem("Об автошколе", R.drawable.info_icon, "AboutSchoolScreen")
}