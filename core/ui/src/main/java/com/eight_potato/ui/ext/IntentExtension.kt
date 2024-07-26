package com.eight_potato.ui.ext

import android.content.Intent
import android.os.Build
import java.io.Serializable

inline fun <reified T: Serializable> Intent.getSerializable(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializableExtra(key, T::class.java)
    } else getSerializableExtra(key) as? T
}