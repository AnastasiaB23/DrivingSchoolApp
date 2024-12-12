package com.example.drivingschoolapp.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.drivingschoolapp.R
import com.example.drivingschoolapp.ui.theme.blue
import com.example.drivingschoolapp.ui.theme.light_grey_main


@Composable
fun AboutSchoolScreen() {
    val items = listOf(
        "Юридический адрес" to "г. Москва, Староватутинский проезд, д.10, стр.17.",
        "Фактический адрес" to "г. Москва, проезд Шокальского, д.41.",
        "Режим и график работы" to "Понедельник - Суббота 10.00 - 18.00 (без перерыва), Воскресенье - выходной.",
        "Контактные телефоны" to "+7 (495) 643-04-35, +7 (909) 963-40-40.",
        "Адрес электронной почты" to "av-maksima@yandex.ru."
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
                text = "Контактная информация",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        itemsIndexed(items) { index, item ->
            val (title, description) = item
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 4.dp)
                    .clickable { expandedSection = if (expandedSection == index) -1 else index },
                colors = CardDefaults.cardColors(containerColor = light_grey_main)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
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
                    Spacer(modifier = Modifier.height(16.dp))

                    AnimatedVisibility(visible = expandedSection == index) {
                        Text(
                            text = description, modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                }

            }

        }
        item {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Мы на карте:",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(0.7f)

            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(200.dp)
                    .background(Color.Transparent)

                    .paint(
                        painter = painterResource(id = R.drawable.map)
                    )
                    .border(1.dp, blue, RectangleShape),

                )
        }
    }
}

