package com.eight_potato.ui.navigator

import android.content.Context
import android.content.Intent

interface Navigator {
    fun getSearchAddressActivityIntent(
        context: Context
    ): Intent
}