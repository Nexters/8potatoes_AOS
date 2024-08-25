plugins {
    id("hyusik.android.feature")
}

android {
    namespace = "com.eight_potato.rest"
}

dependencies {
    implementation(libs.google.services.location)
    implementation(libs.naverMap)
    implementation(libs.coil)

    implementation(project(":core:common"))
}