package com.sunildhiman90.typesafenavkmpcompose

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "TypeSafeNavigationKMPCompose",
    ) {
        App()
    }
}