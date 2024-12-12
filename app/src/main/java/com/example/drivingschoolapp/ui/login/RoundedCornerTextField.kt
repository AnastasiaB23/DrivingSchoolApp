package com.example.drivingschoolapp.ui.login

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.drivingschoolapp.ui.theme.grey_1
import com.example.drivingschoolapp.ui.theme.light_grey_main

@Composable
fun RoundedCornerTextField(
    text: String, label: String, onValueChange: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = {
            onValueChange(it)

        },
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,

            ),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, grey_1, RoundedCornerShape(10.dp)),
        label = {
            Text(text = label, color = light_grey_main)
        },
        singleLine = true
    )
}