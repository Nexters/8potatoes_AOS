plugins {
    id("hyusik.android.feature")
}

android {
    namespace = "com.eight_potato.search"
}

dependencies {
    implementation(libs.google.services.location)
    implementation(libs.naverMap)
}