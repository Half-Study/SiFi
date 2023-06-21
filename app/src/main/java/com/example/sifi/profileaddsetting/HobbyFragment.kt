package com.example.sifi.profileaddsetting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.sifi.R
import com.example.sifi.databinding.FragmentHobbyBinding

class HobbyFragment : Fragment() {
    lateinit var hobbyList: List<Button>

    override fun onStop() {
        super.onStop()
        Log.d("daeYoung", "Hobby 프라그먼트 onStop() 호출")
        val selectHobbyList = hobbyList.filter { it.isSelected }.map { it.text.toString() }
        val mainActivity = activity as ProfileSettingActivity
        mainActivity.receiveData2(this, mapOf("hobby" to selectHobbyList))
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHobbyBinding.inflate(inflater, container, false)
        hobbyList = listOf(
            binding.hobby1,
            binding.hobby2,
            binding.hobby3,
            binding.hobby4,
            binding.hobby5,
            binding.hobby6,
            binding.hobby7,
            binding.hobby8,
            binding.hobby9,
            binding.hobby10,
            binding.hobby11,
            binding.hobby12,
            binding.hobby13,
            binding.hobby14,
            binding.hobby15,
            binding.hobby16,
            binding.hobby17,
            binding.hobby18,
            binding.hobby19,
            binding.hobby20,
            binding.hobby21,
            binding.hobby22,
            binding.hobby23,
        )
        hobbyList.forEach {button ->
            button.setOnClickListener {
                it.isSelected = !it.isSelected
            }
        }

        return binding.root
    }
}