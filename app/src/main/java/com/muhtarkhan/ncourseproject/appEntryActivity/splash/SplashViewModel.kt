package com.muhtarkhan.ncourseproject.appEntryActivity.splash


import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.muhtarkhan.ncourseproject.R


class SplashViewModel(
) : ViewModel() {


    fun dispatch(
        event: SplashEvent,
        navController: NavController
    ) {
        when (event) {
            SplashEvent.onCLick -> {
               navController.navigate(R.id.action_splashFragment_to_loginFragment)
            }
        }
    }
}