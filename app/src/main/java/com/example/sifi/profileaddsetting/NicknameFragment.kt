package com.example.sifi.profileaddsetting

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentContainerView
import com.example.sifi.MainActivity
import com.example.sifi.R

class NicknameFragment : Fragment() /*, ProfileSettingActivity.onBackPressedListener */ {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nickname, container, false)
    }

//    override fun onBackPressed() {
//        Log.d("daeYoung", "닉네임 프라그먼트 뒤로가기 실행")
//        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
//    }

}