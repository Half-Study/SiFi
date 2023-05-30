package com.example.sifi.profileaddsetting

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sifi.R
import com.example.sifi.databinding.ActivityProfileSettingBinding
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class ProfileSettingActivity : AppCompatActivity() {

    private val fragmentList: List<Fragment> = listOf(
        RegionFragment(),
        NicknameFragment(),
        MBTIFragment()
    )

    private lateinit var nextBtn: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var nickNameEdit: EditText


    private var cursor = 1

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // 뒤로 가기 이벤트 처리
            Log.d("daeYoung", "뒤로가기 클릭")
            if (cursor > 0) {
                supportFragmentManager.popBackStack()
                cursor--
                progressBar.setProgress(progressBar.progress -  10)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProfileSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.onBackPressedDispatcher.addCallback(this, callback)

        nextBtn = binding.nextBtn
        progressBar = binding.progress

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
                progressBar.setProgress(progressBar.progress + 10)
//                getInterval().subscribe {
//                    progressBar.setProgress(progressBar.progress + it.toInt())
//                }
            }
            when(cursor) {
                1 -> {}
                2 -> {}
                else -> {}
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                Log.d("daeYoung", "뒤로가기 클릭")
                if (cursor > 0) {
                    supportFragmentManager.popBackStack()
                    cursor--
                    progressBar.setProgress(progressBar.progress - 10)
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

//    private fun getInterval(): Observable<Long> =
//        Observable.interval(100L, TimeUnit.MILLISECONDS).map { interval ->
//            interval + 10
//        }.take(1)

}

