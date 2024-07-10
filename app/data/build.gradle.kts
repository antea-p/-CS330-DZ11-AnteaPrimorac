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

    implementation(libs.google.gson)
    implementation(libs.square.retrofit)
    implementation(libs.square.retrofit.converter.gson)
    implementation(libs.square.okhttp.logging.interceptor)

    testImplementation(libs.junit)
    testImplementation(libs.mockwebserver)
    testImplementation(libs.coroutines.test)
}