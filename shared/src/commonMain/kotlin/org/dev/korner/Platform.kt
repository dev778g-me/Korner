package org.dev.korner

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform