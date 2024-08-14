package com.eight_potato.ui.manager

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.eight_potato.ui.permission.RequestPermissionDialog

/**
 * 위치 정보 관리
 */
class LocationManager (
    private val context: Context
){
    private fun checkHasPermission(): Boolean {
        return LOCATION_PERMISSION.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    fun checkLocationPermissionAndShowDialog(
        onResponse: (Boolean) -> Unit
    ) {
        if (checkHasPermission()) {
            onResponse(true)
            return
        }

        (context as? FragmentActivity)?.let {
            RequestPermissionDialog()
                .setOnResponse(onResponse)
                .show(it.supportFragmentManager, "Location_Permission")
        }
    }

    companion object {
        val LOCATION_PERMISSION = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }
}