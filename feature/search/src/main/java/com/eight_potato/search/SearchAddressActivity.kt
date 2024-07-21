package com.eight_potato.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.eight_potato.search.ui.SearchAddressHeader
import com.eight_potato.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchAddressActivity : BaseActivity() {
    @Composable
    override fun Body() {
        Scaffold(
            topBar = {
                SearchAddressHeader { finish() }
            }
        ) {
            Column(
                modifier = Modifier.padding(it)
            ) {

            }
        }
    }
}