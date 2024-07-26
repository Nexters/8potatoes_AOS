plugins {
    id ("hyusik.android.application")
    id ("hyusik.android.hilt")
    id ("hyusik.android.application.compose")
}

android {
    namespace = "com.eight_potato.hyusikmatju"
}

dependencies {
    implementation(project(":feature:search"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:ui"))

    /**
     * Clean Architecture
     */
    implementation(project(":core:data"))
}