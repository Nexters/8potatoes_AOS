plugins {
    id("hyusik.android.library")
    id("hyusik.android.hilt")
}

android {
    namespace = "com.eight_potato.data"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    
    implementation(libs.bundles.retrofit)
}