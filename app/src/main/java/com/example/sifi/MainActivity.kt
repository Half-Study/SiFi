package com.example.sifi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sifi.databinding.ActivityMainBinding
import com.example.sifi.profileaddsetting.RegionFragment
import com.example.sifi.profileaddsetting.ProfileSettingActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startActivity(Intent(this, ProfileSettingActivity::class.java))

    }
}