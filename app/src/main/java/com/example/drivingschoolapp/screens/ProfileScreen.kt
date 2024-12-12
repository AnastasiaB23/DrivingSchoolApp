package com.example.drivingschoolapp.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.drivingschoolapp.MainViewModel
import com.example.drivingschoolapp.ROOM.UserEntity
import com.example.drivingschoolapp.ui.theme.black_1
import com.example.drivingschoolapp.ui.theme.grey_1
import com.example.drivingschoolapp.ui.theme.light_orange_1
import com.example.drivingschoolapp.ui.theme.orange_2


@Composable
fun ProfileScreen(mainViewModel: MainViewModel) {
    val currentUser by mainViewModel.currentUser.collectAsState()

    var name by remember { mutableStateOf(TextFieldValue("")) }
    var surname by remember { mutableStateOf(TextFieldValue("")) }
    var patronymic by remember { mutableStateOf(TextFieldValue("")) }
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var profileImage by remember { mutableStateOf("default_image") }

    val imagePickerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                profileImage = it.toString()
            }
        }

    LaunchedEffect(currentUser) {
        currentUser?.let {
            name = TextFieldValue(it.name)
            surname = TextFieldValue(it.surname)
            patronymic = TextFieldValue(it.patronymic)
            phoneNumber = TextFieldValue(it.phoneNumber)
            email = TextFieldValue(it.email)
            profileImage = it.profile_image
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(shape = RoundedCornerShape(100.dp))
                .background(Color.Transparent)
                .border(1.dp, black_1, RoundedCornerShape(100.dp))
                .clickable {
                    imagePickerLauncher.launch("image/*")
                }, contentAlignment = Alignment.Center
        ) {
            if (profileImage.isNotEmpty()) {
                Image(
                    painter = rememberAsyncImagePainter(profileImage),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = RoundedCornerShape(100.dp))
                        .background(Color.Transparent)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        listOf(Triple("Имя", name) { value: TextFieldValue -> name = value },
            Triple("Фамилия", surname) { value: TextFieldValue -> surname = value },
            Triple("Отчество", patronymic) { value: TextFieldValue -> patronymic = value },
            Triple("Телефон", phoneNumber) { value: TextFieldValue -> phoneNumber = value },
            Triple("Электронная почта", email) { value: TextFieldValue ->
                email = value
            }).forEach { (label, value, onValueChange) ->
            TextField(

                value = value,
                onValueChange = onValueChange,
                label = { Text(label) },
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = light_orange_1,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = grey_1,
                    focusedLabelColor = black_1

                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .border(1.dp, grey_1, RoundedCornerShape(10.dp))
                    .padding(5.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.height(2.dp))

        Button(
            onClick = {
                val updatedUser = currentUser?.copy(
                    name = name.text,
                    surname = surname.text,
                    patronymic = patronymic.text,
                    phoneNumber = phoneNumber.text,
                    email = email.text,
                    profile_image = profileImage
                ) ?: UserEntity(
                    name = name.text,
                    surname = surname.text,
                    patronymic = patronymic.text,
                    phoneNumber = phoneNumber.text,
                    email = email.text,
                    profile_image = profileImage
                )

                if (currentUser == null) {
                    mainViewModel.insertUser(updatedUser)
                } else {
                    mainViewModel.updateUser(updatedUser)
                }
            }, shape = RoundedCornerShape(10.dp), border = BorderStroke(1.dp, grey_1),

            colors = ButtonDefaults.buttonColors(
                containerColor = orange_2,

                ), modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(8.dp)
        ) {
            Text("Сохранить", color = black_1)
        }
    }
}