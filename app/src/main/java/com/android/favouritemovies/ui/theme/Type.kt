package com.android.favouritemovies.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.android.favouritemovies.R

// Set of Material typography styles to start with
val roboto = FontFamily(Font(R.font.roboto_regular))
val Typography = Typography(
    defaultFontFamily = roboto,
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    )
)

val Typography.topNavigationTitle: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.W600,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
    }

val Typography.itemTitle: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.W600,
            fontSize = 16.sp,
            lineHeight = 18.75.sp
        )
    }

val Typography.itemDate: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
            lineHeight = 16.41.sp,
        )
    }

val Typography.itemVoteCount: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.W600,
            fontSize = 14.sp,
            lineHeight = 16.41.sp,
            letterSpacing = 0.12.sp
        )
    }
val Typography.itemVoteAverage: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.W600,
            fontSize = 14.sp,
            lineHeight = 16.41.sp,
            letterSpacing = 0.12.sp
        )
    }