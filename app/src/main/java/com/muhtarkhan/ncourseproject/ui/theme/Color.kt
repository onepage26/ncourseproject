package com.muhtarkhan.ncourseproject.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


data class Colors(
    val primary: Color,
    val textmain: Color,
    val link: Color,
    val gray900: Color,
    val gray800: Color,
    val white: Color,
)



val appColors = Colors(

    primary = Color(0xffDF1323),
    textmain = Color(0xff1F1F1F),
    link = Color(0xff16A1ED),
    gray900 = Color(0xffB2B2B2),
    gray800 = Color(0xffF6F6F6),
    white = Color(0xffffffff),
)

internal val LocalColors = staticCompositionLocalOf { appColors }