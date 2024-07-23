plugins {
    id("hyusik.android.library")
    id("hyusik.android.hilt")
}

android {
    namespace = "com.eight_potato.network"
}

dependencies {
    implementation(libs.bundles.retrofit)
}