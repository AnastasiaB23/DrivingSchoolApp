package com.example.drivingschoolapp.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.drivingschoolapp.R
import com.example.drivingschoolapp.ui.login.data.MainScreenDataObject
import com.example.drivingschoolapp.ui.theme.black_1
import com.example.drivingschoolapp.ui.theme.red_2
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


@Composable
fun LoginScreen(
    onNavigateToMainScreen: (MainScreenDataObject) -> Unit
) {
    val auth = remember { Firebase.auth }

    val errorState = remember {
        mutableStateOf("")
    }

    val emailState = remember {
        mutableStateOf("")
    }

    val passwordState = remember {
        mutableStateOf("")
    }

    Image(
        painter = painterResource(id = R.drawable.cars_on_road),
        contentDescription = "cars_im",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 40.dp, end = 40.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Регистрация/вход",
            color = black_1,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            fontFamily = FontFamily.Default
        )

        Spacer(modifier = Modifier.height(30.dp))

        RoundedCornerTextField(
            text = emailState.value, label = "Электронная почта"
        ) {
            emailState.value = it
        }
        Spacer(modifier = Modifier.height(20.dp))


        RoundedCornerTextField(
            text = passwordState.value, label = "Пароль"
        ) {
            passwordState.value = it
        }
        Spacer(modifier = Modifier.height(20.dp))

        if (errorState.value.isNotEmpty()) {
            Text(
                text = errorState.value, color = red_2, textAlign = TextAlign.Center
            )
        }
        LoginButton(text = "Войти") {
            signIn(auth, emailState.value, passwordState.value, onSignInSuccess = { navData ->
                onNavigateToMainScreen(navData)
            }, onSignInFailure = { error ->
                errorState.value = error
            })

        }
        Spacer(modifier = Modifier.height(15.dp))
        LoginButton(text = "Зарегистрироваться") {
            signUp(auth, emailState.value, passwordState.value, onSignUpSuccess = { navData ->
                onNavigateToMainScreen(navData)

            }, onSignUpFailure = { error ->
                errorState.value = error
            })
        }

//        Button(onClick = {
//            signOut(auth)
//
//        }) {
//            Text(text = "Sign Out")
//        }
    }
}

private fun signUp(
    auth: FirebaseAuth,
    email: String,
    password: String,
    onSignUpSuccess: (MainScreenDataObject) -> Unit,
    onSignUpFailure: (String) -> Unit
) {
    if (email.isBlank() || password.isBlank()) {
        onSignUpFailure("Поля почты и пароля не могут быть пустыми")
        return
    }
    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                onSignUpSuccess(
                    MainScreenDataObject(
                        it.result.user?.email!!
                    )
                )
            }
        }.addOnFailureListener {
            onSignUpFailure(it.message ?: "Возникла ошибка, попробуйте ещё раз")
        }
}

private fun signIn(
    auth: FirebaseAuth,
    email: String,
    password: String,
    onSignInSuccess: (MainScreenDataObject) -> Unit,
    onSignInFailure: (String) -> Unit
) {
    if (email.isBlank() || password.isBlank()) {
        onSignInFailure("Неверные данные")
        return
    }
    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                onSignInSuccess(
                    MainScreenDataObject(
                        it.result.user?.email!!
                    )
                )
            }
        }.addOnFailureListener {
            onSignInFailure(it.message ?: "Возникла ошибка, попробуйте ещё раз")
        }
}


private fun signOut(auth: FirebaseAuth) {
    auth.signOut()
}