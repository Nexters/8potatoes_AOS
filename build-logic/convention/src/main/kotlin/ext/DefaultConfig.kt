package ext

import com.android.build.api.dsl.ApplicationExtension
import consts.Constants

internal fun ApplicationExtension.configureDefault() {
    defaultConfig {
        applicationId = Constants.packageName
        targetSdk = Constants.targetSdk
        multiDexEnabled = true
        versionCode = Constants.versionCode
        versionName = Constants.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}