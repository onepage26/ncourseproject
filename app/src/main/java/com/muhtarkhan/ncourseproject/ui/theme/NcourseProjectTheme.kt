package com.muhtarkhan.ncourseproject.ui.theme

import android.os.Build
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

@Composable
fun NcourseProjectTheme(
    content: @Composable () -> Unit
) {
    val rippleIndication = ripple()
    val context = LocalContext.current

    CompositionLocalProvider(
        LocalColors provides appColors,
        LocalIndication provides rippleIndication,
        LocalTypography provides appTypography,
    ) {
        content()
    }
}