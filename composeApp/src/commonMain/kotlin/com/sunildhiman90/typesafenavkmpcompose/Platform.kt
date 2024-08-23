package com.sunildhiman90.typesafenavkmpcompose

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform