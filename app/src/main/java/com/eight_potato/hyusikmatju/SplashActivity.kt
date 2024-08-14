package com.eight_potato.hyusikmatju

import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.hyusikmatju.main.MainActivity
import com.eight_potato.ui.base.BaseActivity
import com.eight_potato.ui.manager.LocationManager

class SplashActivity : BaseActivity() {
    private val locationManager by lazy { LocationManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationManager.checkLocationPermissionAndShowDialog {
            startActivity(
                Intent(this, MainActivity::class.java)
            )
            finish()
        }
    }

    @Composable
    override fun Body() {
        Box(modifier = Modifier.fillMaxSize().background(Colors.White))
    }
}