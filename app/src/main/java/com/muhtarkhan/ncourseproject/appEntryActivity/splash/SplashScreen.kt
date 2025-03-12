package com.muhtarkhan.ncourseproject.appEntryActivity.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.muhtarkhan.ncourseproject.R
import com.muhtarkhan.ncourseproject.ui.theme.LocalColors


@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LocalColors.current.primary),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "Centered Image",
            modifier = Modifier.size(200.dp)
                .clickable { viewModel.dispatch(SplashEvent.onCLick, navController) }
        )
    }
}