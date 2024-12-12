package com.example.drivingschoolapp.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.drivingschoolapp.ui.theme.carrot
import com.example.drivingschoolapp.ui.theme.orange_1
import com.example.drivingschoolapp.ui.theme.yellow

@Composable
fun ScheduleScreen() {
    val items = listOf(
        "Расписание занятий по теории" to "С 01.01.2025 - 16.03.2025." + "\nПонедельник и среда, с 20:00 до 22:00",
        "Расписание занятий по вождению" to "Обговаривается индивидуально с инструктором по вождению",
        "График промежуточных зачётов" to "4,8,12,16,20 занятия",
    )

    var expandedSection by remember { mutableStateOf(-1) }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth(0.8f)
            .padding(top = 65.dp, bottom = 85.dp)
    ) {
        item {
            Text(
                text = "ИНФОРМАЦИЯ ПО РАСПИСАНИЮ",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 20.dp)
            )
        }

        itemsIndexed(items) { index, item ->
            val (title, description) = item
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 4.dp)
                    .clickable { expandedSection = if (expandedSection == index) -1 else index },
                colors = CardDefaults.cardColors(
                    containerColor = when (index) {
                        0 -> yellow
                        1 -> orange_1
                        else -> carrot
                    }
                )
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = title, fontWeight = FontWeight.Bold
                        )
                        Icon(
                            imageVector = if (expandedSection == index) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )

                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    AnimatedVisibility(visible = expandedSection == index) {
                        Text(
                            text = description, modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                }

            }

        }
    }
}