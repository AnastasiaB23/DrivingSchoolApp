package com.example.drivingschoolapp.ui.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.drivingschoolapp.ui.theme.black_1
import com.example.drivingschoolapp.ui.theme.grey_1
import com.example.drivingschoolapp.ui.theme.light_yellow_main

@Composable
fun LoginButton(
    text: String, onClick: () -> Unit
) {
    Button(
        onClick = {
            onClick()
        }, shape = RoundedCornerShape(10.dp), border = BorderStroke(1.dp, grey_1),

        colors = ButtonDefaults.buttonColors(
            containerColor = light_yellow_main,


            ), modifier = Modifier.fillMaxWidth(0.7f)

    ) {
        Text(text = text, color = black_1, fontSize = 15.sp)
    }
}