package com.example.sifi.profileaddsetting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.sifi.databinding.FragmentIntroduceBinding

class IntroduceFragment : Fragment() {
    lateinit var editText: EditText

    override fun onStop() {
        super.onStop()
        Log.d("daeYoung", "Introduce 프라그먼트 onStop() 호출")
        val mainActivity = activity as ProfileSettingActivity
        mainActivity.receiveData(this, mapOf("introduce" to (editText.text.toString())) )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentIntroduceBinding.inflate(inflater, container, false)
        editText = binding.editText


        return binding.root
    }


}