package com.example.sifi.profileaddsetting

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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProfileSettingActivity : AppCompatActivity() {

    private val fragmentList: List<Fragment> = listOf(
        SexFragment(),
        RegionFragment(),
        NicknameFragment(),
        JobFragment(),
        HobbyFragment(),
        MBTIFragment(),
        IntroduceFragment(),
        FinishFragment(),
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
                changeState()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProfileSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.onBackPressedDispatcher.addCallback(this, callback)

        nextBtn = binding.nextBtn
        progressBar = binding.progress.apply { progress = 13 }

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frameLayout, SexFragment())
            .addToBackStack(null)
            .commit()


        nextBtn.setOnClickListener {
            if (cursor < fragmentList.size) {
                changeFragment(fragmentList[cursor++])
                progressBar.progress = progressBar.progress + 13
                changeState()

            }
        }

    }

    fun changeState() {
        when(cursor) {
            1 -> {
                Log.d("daeYoung", "cursor는 ${cursor}번째")

            }
            2 -> {
                Log.d("daeYoung", "cursor는 ${cursor}번째")

            }
            5 -> {
                Log.d("daeYoung", "cursor는 ${cursor}번째")
                nextBtn.isEnabled = true
            }
            6 -> {
                Log.d("daeYoung", "cursor는 ${cursor}번째")
                val mbtiFragment = fragmentList[5] as MBTIFragment
                CoroutineScope(Dispatchers.Main).launch {
                    mbtiFragment.isNext().collectLatest {
                        nextBtn.isEnabled = it.isNotBlank()
                    }
                }

            }
            else -> {}
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                Log.d("daeYoung", "뒤로가기 클릭")
                if (cursor > 0) {
                    supportFragmentManager.popBackStack()
                    cursor--
                    progressBar.progress = progressBar.progress - 13
                    changeState()
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

    fun receiveData(fragment: Fragment, data: Map<String, String>) {
        Log.d("daeYoung", "${fragment.toString()} 프라그먼트의 데이터: ${data.toString()}")
    }

    fun receiveData2(fragment: Fragment, data: Map<String, List<String>>) {
        Log.d("daeYoung", "${fragment.toString()} 프라그먼트의 데이터: ${data.toString()}")
    }

}

