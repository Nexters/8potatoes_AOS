plugins {
    id("hyusik.android.feature")
}

android {
    namespace = "com.eight_potato.rest"
}

dependencies {
    implementation(libs.google.services.location)
    implementation(libs.naverMap)

    implementation(project(":core:common"))
}