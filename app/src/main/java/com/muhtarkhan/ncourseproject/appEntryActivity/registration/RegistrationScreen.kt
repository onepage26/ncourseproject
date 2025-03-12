package com.muhtarkhan.ncourseproject.appEntryActivity.registration

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.muhtarkhan.ncourseproject.appEntryActivity.login.HeaderLog

import com.muhtarkhan.ncourseproject.R
import com.muhtarkhan.ncourseproject.ui.theme.LocalColors
import com.muhtarkhan.ncourseproject.ui.theme.LocalTypography

@Composable
fun RegistrationScreen(
    registrationState: RegistrationState,
    onEvent: (RegistrationEvent, NavController) -> Unit,
    navController: NavController
) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }


    LaunchedEffect(registrationState) {
        errorMessage = if (registrationState is RegistrationState.Error) registrationState.message else ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LocalColors.current.gray800)
    ) {
        HeaderLog()

        Spacer(Modifier.weight(1f))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Зарегистрироваться",
                color = LocalColors.current.textmain,
                style = LocalTypography.current.titleLarge,
            )
            Spacer(modifier = Modifier.height(36.dp))

            TextField(
                value = login,
                onValueChange = { login = it },
                label = { Text("Email") },
                colors = TextFieldDefaults.colors().copy(
                    unfocusedContainerColor = LocalColors.current.white,
                    focusedContainerColor = LocalColors.current.white,
                    unfocusedTextColor = LocalColors.current.textmain,
                    focusedTextColor = LocalColors.current.textmain,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                colors = TextFieldDefaults.colors().copy(
                    unfocusedContainerColor = LocalColors.current.white,
                    focusedContainerColor = LocalColors.current.white,
                    unfocusedTextColor = LocalColors.current.textmain,
                    focusedTextColor = LocalColors.current.textmain,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(8.dp))


            AnimatedVisibility(visible = errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(36.dp))

            Button(
                onClick = {
                    onEvent(RegistrationEvent.Register(login, password), navController)
                },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Зарегистрироваться")
            }
        }

        Spacer(Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 36.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Есть аккаунт?",
                color = LocalColors.current.gray900,
                style = LocalTypography.current.labelLarge,
            )
            Text(
                text = "Войти",
                color = LocalColors.current.primary,
                style = LocalTypography.current.labelLarge,
                modifier = Modifier
                    .clickable {
                        onEvent(RegistrationEvent.NavigateToLogin, navController)
                    }
                    .padding(start = 4.dp)
            )
        }
    }

    when (registrationState) {
        is RegistrationState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
        is RegistrationState.Success -> {
            LaunchedEffect(Unit) {
                navController.navigate(R.id.action_registrationFragment_to_homeFragment)
            }
        }
        else -> {}
    }
}
