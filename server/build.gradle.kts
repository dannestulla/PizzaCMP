plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "br.gohan.pizzacmp"
version = "1.0.0"
application {
    mainClass.set("br.gohan.pizzacmp.ApplicationKt")
    applicationDefaultJvmArgs =
        listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.websocket)
    implementation(libs.ktor.sessions)
    implementation(libs.ktor.logging)
    implementation(libs.ktor.headers)

    testImplementation(libs.ktor.server.tests)
}