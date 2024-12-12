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
import androidx.compose.ui.unit.sp
import com.example.drivingschoolapp.ui.theme.light_orange_2
import com.example.drivingschoolapp.ui.theme.light_red_1
import com.example.drivingschoolapp.ui.theme.light_red_2
import com.example.drivingschoolapp.ui.theme.light_yellow_1
import com.example.drivingschoolapp.ui.theme.light_yellow_2

@Composable
fun ExamsScreen() {

    val items = listOf(
        "ЗАЧЕТ 1" to "Тут описание первого зачёта. Он самый простой)",
        "ЗАЧЕТ 2" to "Состоит из двух блоков по 5 вопросов.",
        "ЗАЧЕТ 3" to "Тут описание третьего зачета. Он состоит из 15 вопросов",
        "ЗАЧЕТ 4" to "Тут уже полный билет на 20 вопросов",
        "ЭКЗАМЕН АВТОШКОЛА" to "Финальный зачет для выпуска из автошколы",
        "ЭКЗАМЕН ГАИ" to "В целом такой же, как в автошколе, но более тревожный"
    )

    var expandedIndex by remember { mutableStateOf(-1) }

    LazyColumn(
        verticalArrangement = Arrangement.Center, modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                fontSize = 20.sp,
                text = "Структура и темы зачетов",
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 32.dp),
            )
        }

        itemsIndexed(items) { index, item ->
            val (title, description) = item

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable {
                        expandedIndex = if (expandedIndex == index) -1 else index
                    }, colors = CardDefaults.cardColors(
                    containerColor = when (index) {
                        0 -> light_yellow_1
                        1 -> light_yellow_2
                        2 -> light_orange_2
                        3 -> light_red_1
                        else -> light_red_2
                    }
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = title, fontWeight = FontWeight.Bold
                        )
                        Icon(
                            imageVector = if (expandedIndex == index) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                    AnimatedVisibility(visible = expandedIndex == index) {
                        Column {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = description,
                            )
                        }
                    }
                }
            }
        }
    }
}
