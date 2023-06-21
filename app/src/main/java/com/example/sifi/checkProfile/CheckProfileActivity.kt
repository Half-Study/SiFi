package com.example.sifi.checkProfile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sifi.databinding.ActivityCheckProfileBinding
import com.example.sifi.databinding.ActivityProfileSettingBinding

class CheckProfileActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityCheckProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val str = intent.getStringExtra("uid")
        Log.d("daeYoung", "uid: $str")

    }
}