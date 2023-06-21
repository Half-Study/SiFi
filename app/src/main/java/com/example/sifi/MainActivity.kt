package com.example.sifi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sifi.Fragment.ChatFragment
import com.example.sifi.Fragment.MypageFragment
import com.example.sifi.Fragment2.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById<BottomNavigationView>(R.id.bottomnav)
        loadFragment(HomeFragment())

        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home->{
                    loadFragment(HomeFragment())
                    true
                }
                R.id.chat->{
                    loadFragment(ChatFragment())
                    true
                }
                R.id.myPage->{
                    loadFragment(MypageFragment())
                    true
                }
                else -> false
            }
        }

    }
    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}