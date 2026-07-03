package io.jadu.phoenix

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
