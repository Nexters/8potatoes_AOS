package com.eight_potato.ui.base

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import com.eight_potato.designsystem.theme.HyusikMatjuTheme
import com.eight_potato.ui.databinding.ActivityBaseBinding

/**
 * 기본 Activity
 */
abstract class BaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observe()
        binding.container.setContent {
            HyusikMatjuTheme(
                content = { Body() }
            )
        }
    }

    @Composable
    abstract fun Body()
    open fun observe() {}
}