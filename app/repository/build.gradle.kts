plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(":app:common"))
    implementation(project(":app:data"))


    implementation(libs.jetbrains.kotlinx.coroutines)
    implementation(libs.jetbrains.kotlinx.coroutines.android)
    implementation(libs.square.retrofit)
}