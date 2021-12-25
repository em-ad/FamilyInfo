package com.emad.familyinfo.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.emad.familyinfo.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        color = Color.White,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

val myFont = FontFamily(
    Font(R.font.app_font, FontWeight.Normal),
)

val myFontBold = FontFamily(
    Font(R.font.app_font, FontWeight.ExtraBold),
)