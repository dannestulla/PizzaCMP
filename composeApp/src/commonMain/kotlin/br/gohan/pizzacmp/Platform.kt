package br.gohan.pizzacmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform