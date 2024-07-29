plugins {
    id("hyusik.android.library")
    id("hyusik.android.library.compose")
    id("hyusik.android.hilt")
}

android {
    namespace = "com.eight_potato.ui"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:domain"))

    implementation(libs.naverMap)
}