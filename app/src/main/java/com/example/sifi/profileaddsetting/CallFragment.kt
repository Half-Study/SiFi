package com.example.sifi.profileaddsetting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.sifi.databinding.FragmentCallBinding

class CallFragment : Fragment() {
    lateinit var callEdit: EditText

    override fun onStop() {
        super.onStop()
        val mainActivity = activity as ProfileSettingActivity
        mainActivity.receiveData(this, mapOf("call" to callEdit.text.toString()) )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCallBinding.inflate(inflater, container, false)
        callEdit = binding.callEdit

        return binding.root
    }

}