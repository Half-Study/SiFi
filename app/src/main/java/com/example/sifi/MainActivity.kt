package com.example.sifi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sifi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val btn = binding.btn

        btn.setOnClickListener {
            changeFragment()
        }




    }

    private fun changeFragment() {
        val transaction = supportFragmentManager.beginTransaction()
            .add(R.id.frameLayout, NicknameFragment())
        transaction.commit()
    }
}