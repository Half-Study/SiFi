package com.example.sifi.profileaddsetting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.sifi.R
import com.example.sifi.databinding.ActivityProfileSettingBinding

class ProfileSettingActivity : AppCompatActivity() {

    private val fragmentList: List<Fragment> = listOf(
        RegionFragment(),
        NicknameFragment()
    )

    private lateinit var nextBtn: Button

    private var cursor = 1

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // 뒤로 가기 이벤트 처리
            Log.d("daeYoung", "뒤로가기 클릭")
            if (cursor > 0) {
                supportFragmentManager.popBackStack()
                cursor--
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProfileSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.onBackPressedDispatcher.addCallback(this, callback)

        nextBtn = binding.nextBtn

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frameLayout, RegionFragment())
            .addToBackStack(null)
            .commit()


        nextBtn.setOnClickListener {
            if (cursor < fragmentList.size) {
                Log.d("daeYoung", "${cursor}, ${fragmentList[cursor]} " )
                changeFragment(fragmentList[cursor++])
            }
        }

    }

//    interface onBackPressedListener {
//        fun onBackPressed()
//    }
//
//    override fun onBackPressed() {
//        //아래와 같은 코드를 추가하도록 한다
//        //해당 엑티비티에서 띄운 프래그먼트에서 뒤로가기를 누르게 되면 프래그먼트에서 구현한 onBackPressed 함수가 실행되게 된다.
//        val fragmentList = supportFragmentManager.fragments
//        Log.d("daeYoung", "대괄호: ${fragmentList}")
//        for (fragment in fragmentList) {
//            if (fragment is onBackPressedListener) {
//                (fragment as onBackPressedListener).onBackPressed()
//                return
//            }
//        }
//
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                Log.d("daeYoung", "뒤로가기 클릭")
                if (cursor > 0) {
                    supportFragmentManager.popBackStack()
                    cursor--
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun changeFragment(fragment: Fragment, tag: String? = null) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment, tag)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}