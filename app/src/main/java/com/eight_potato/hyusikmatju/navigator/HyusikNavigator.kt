package com.eight_potato.hyusikmatju.navigator

import android.content.Context
import android.content.Intent
import com.eight_potato.search.SearchAddressActivity
import com.eight_potato.ui.navigator.Navigator
import javax.inject.Inject

class HyusikNavigator @Inject constructor() : Navigator {
    override fun getSearchAddressActivityIntent(
        context: Context
    ): Intent {
        return Intent(context, SearchAddressActivity::class.java)
    }
}