package com.example.sifi.profileaddsetting

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withStarted
import com.example.sifi.Login.LoginActivity
import com.example.sifi.R
import com.example.sifi.data.User
import com.example.sifi.databinding.ActivityProfileSettingBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileSettingActivity : AppCompatActivity() {
//    lateinit var myViewModel: MyViewModel

    val binding by lazy {
        ActivityProfileSettingBinding.inflate(layoutInflater)
    }
    lateinit var uid: String
    val database by lazy { Firebase.database }

    val userData: MutableMap<String, List<String>> = mutableMapOf()

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

    private var cursor = 1

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // 뒤로 가기 이벤트 처리
            Log.d("daeYoung", "뒤로가기 클릭")
            if (cursor > 0) {
                supportFragmentManager.popBackStack()
                cursor--
                decreaseProgress()
                changeState()
            }
        }
    }

    private val stepProgressAmount = 10

    private fun increaseProgress() {
        binding.progress.progress += stepProgressAmount
    }

    private fun decreaseProgress() {
        binding.progress.progress -= stepProgressAmount
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        this.onBackPressedDispatcher.addCallback(this, callback)

//        binding.progress.min = 0
        binding.progress.max = stepProgressAmount * fragmentList.size
        binding.progress.progress = stepProgressAmount

        uid = intent.getStringExtra("uid") ?: ""

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frameLayout, SexFragment())
            .addToBackStack(null)
            .commit()


        binding.nextBtn.setOnClickListener {
            if (cursor < fragmentList.size) {
                changeFragment(fragmentList[cursor++])
                increaseProgress()
                changeState()
            } else if(cursor == fragmentList.size) {
                ++cursor
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
            3 -> {
                Log.d("daeYoung", "cursor는 ${cursor}번째")
            }
            4 -> {
                Log.d("daeYoung", "cursor는 ${cursor}번째")
            }
            5 -> {
                Log.d("daeYoung", "cursor는 ${cursor}번째")
                binding.nextBtn.isEnabled = true
            }
            6 -> {
                Log.d("daeYoung", "cursor는 ${cursor}번째")
                val mbtiFragment = fragmentList[5] as MBTIFragment

                lifecycleScope.launch {
                    mbtiFragment.withStarted {
                        mbtiFragment.viewLifecycleOwner.lifecycleScope
                    }.launch {
                        mbtiFragment.isNext().collectLatest {
                            withContext(Dispatchers.Main) {
                                binding.nextBtn.isEnabled = it.isNotBlank()
                            }
                            Log.d("daeYoung", "next 버튼의 상태: ${binding.nextBtn.isEnabled}")
                        }
                    }
                }
            }
            7 -> {
                Log.d("daeYoung", "cursor는 ${cursor}번째")
            }
            8 -> { Log.d("daeYoung", "cursor는 ${cursor}번째") }
            9 -> {
                val list = userData.values.toList()[0]
                val user = User(
                    userData.values.toList()[0][0],
                    userData.values.toList()[1][0],
                    userData.values.toList()[2][0],
                    userData.values.toList()[3][0],
                    userData.values.toList()[4],
                    userData.values.toList()[5][0],
                    userData.values.toList()[6][0],

                )
                Log.d("daeYoung", "uid: ${uid}")

                if (uid.isNotBlank()) {
                    Log.d("daeYoung", "firebase store success")
                    val myRef = database.getReference("test")
                    myRef.child(uid).setValue(user)
                }

                finishAffinity()  // 화면 스택 전체 제거
                startActivity(Intent(this, LoginActivity::class.java))
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
                    decreaseProgress()
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
        Log.d("daeYoung", "${fragment.toString()} 프라그먼트의 데이터: ${data}")
        Log.d("daeYoung", "${fragment.toString()} key: ${data.keys}")

        val value = getValue(data = data)
        when(val key = getKey(data = data)) {
            "sex" -> {
                userData[key] = value
                Log.d("daeYoung", "key: ${data.keys}, value: ${data.values}, userData: $userData")
            }
            "region" -> {
                userData[key] = value
                Log.d("daeYoung", "key: ${data.keys}, value: ${data.values}, userData: $userData")
            }
            "nickname" -> {
                userData[key] = value
                Log.d("daeYoung", "key: ${data.keys}, value: ${data.values}, userData: $userData")
            }
            "job" -> {
                userData[key] = value
                Log.d("daeYoung", "key: ${data.keys}, value: ${data.values}, userData: $userData")
            }
            "mbti" -> {
                userData[key] = value
                Log.d("daeYoung", "key: ${data.keys}, value: ${data.values}, userData: $userData")
            }
            "introduce" -> {
                userData[key] = value
                Log.d("daeYoung", "key: ${data.keys}, value: ${data.values}, userData: $userData")
            }
        }
    }

    fun receiveData2(fragment: Fragment, data: Map<String, List<String>>) {
        Log.d("daeYoung", "${fragment.toString()} 프라그먼트의 데이터: ${data.toString()}")
        val value = data.values.toList()[0]
        val key = data.keys.toList()[0]
        userData[key] = value
        Log.d("daeYoung", "key: ${data.keys}, value: ${data.values}, userData: $userData")

    }

    fun getValue(data: Map<String, String>) = data.values.toList()
    fun getKey(data: Map<String, String>) = data.keys.toList()[0]

}

