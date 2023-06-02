package com.example.sifi.profileaddsetting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sifi.R
import com.example.sifi.databinding.FragmentMbtiBinding


class MBTIFragment : Fragment() {

    override fun onStop() {
        super.onStop()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding =  FragmentMbtiBinding.inflate(inflater, container, false)


        return binding.root
    }



}