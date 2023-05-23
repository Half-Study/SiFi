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
        val btn = binding.btn

        btn.setOnClickListener {
            // 회원가입 액티비티 종료
            changeProfileSettingActivity()
        }




    }
    private fun changeProfileSettingActivity() {
        startActivity(Intent(this, ProfileSettingActivity::class.java))
    }
    private fun changeFragment() {
//        val transaction = supportFragmentManager.beginTransaction()
//            .add(R.id.frameLayout, NicknameFragment())
//        transaction.commit()
        val transaction = supportFragmentManager.beginTransaction()
            .add(R.id.frameLayout, RegionFragment())
        transaction.commit()
    }
}