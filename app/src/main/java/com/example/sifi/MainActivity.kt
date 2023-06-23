package com.example.sifi

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sifi.Fragment.MypageFragment
import com.example.sifi.Fragment2.ChatFragment
import com.example.sifi.Fragment2.ChatFragment.Companion.userName
import com.example.sifi.Fragment2.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav : BottomNavigationView
    private var backPressedTime: Long = 0

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if(backPressedTime + 3000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()
        } else {
            Toast.makeText(this, "한번 더 뒤로가기 버튼을 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
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
                    loadFragment(fragment = ChatFragment(), userName)
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
    private fun loadFragment(fragment : ChatFragment, userName: String) {
        fragment.arguments = Bundle().apply {
            //putString("userId", userId)
            putString("userName", userName)
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}