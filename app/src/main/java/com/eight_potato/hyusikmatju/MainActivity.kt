package com.eight_potato.hyusikmatju

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.eight_potato.hyusikmatju.databinding.ActivityMainBinding
import com.eight_potato.search.SearchAddressActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        startActivity(Intent(this, SearchAddressActivity::class.java))
    }
}