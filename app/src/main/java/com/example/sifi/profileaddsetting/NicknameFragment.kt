package com.example.sifi.profileaddsetting

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.FragmentContainerView
import com.example.sifi.MainActivity
import com.example.sifi.R
import com.example.sifi.databinding.FragmentNicknameBinding
import com.example.sifi.databinding.FragmentSexBinding

class NicknameFragment : Fragment() /*, ProfileSettingActivity.onBackPressedListener */ {

    lateinit var nickNameEdit: EditText

    override fun onStop() {
        super.onStop()
        val mainActivity = activity as ProfileSettingActivity
        mainActivity.receiveData(this, mapOf("nickname" to nickNameEdit.text.toString()) )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNicknameBinding.inflate(inflater, container, false)
        nickNameEdit = binding.nickNameEdit

        return binding.root
    }

}