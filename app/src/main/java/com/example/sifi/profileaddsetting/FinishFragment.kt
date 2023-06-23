package com.example.sifi.profileaddsetting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sifi.databinding.ActivityProfileSettingBinding
import com.example.sifi.databinding.FragmentFinishBinding

class FinishFragment : Fragment() {
    lateinit var binding: FragmentFinishBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProfileSettingBinding.inflate(layoutInflater)

        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFinishBinding.inflate(inflater, container, false)

        return binding.root
    }
}