package com.example.sifi.profileaddsetting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.sifi.databinding.FragmentSexBinding

class SexFragment : Fragment() {

    private lateinit var binding: FragmentSexBinding
    private lateinit var btnMale: AppCompatButton
    private lateinit var btnFemale: Button

    override fun onStop() {
        super.onStop()
        Log.d("daeYoung", "성별 프라그먼트 onStop() 호출")
        val mainActivity = activity as ProfileSettingActivity
        if(btnMale.isSelected) { mainActivity.receiveData(this, mapOf("sex" to btnMale.text.toString()) ) }
        else { mainActivity.receiveData(this, mapOf("sex" to btnFemale.text.toString())) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSexBinding.inflate(inflater, container, false)
        btnMale = binding.btnMale
        btnFemale = binding.btnFemale

        btnMale.setOnClickListener {
            it.isSelected = !(it.isSelected)
            btnFemale.isSelected = false
            Log.d("daeYoung", "남자: ${btnMale.isSelected} 여자: ${btnFemale.isSelected}")
        }

        btnFemale.setOnClickListener {
            it.isSelected = !(it.isSelected)
            btnMale.isSelected = false
            Log.d("daeYoung", "남자: ${btnMale.isSelected} 여자: ${btnFemale.isSelected}")
        }


        return binding.root
    }
}