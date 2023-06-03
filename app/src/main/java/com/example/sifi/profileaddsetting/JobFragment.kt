package com.example.sifi.profileaddsetting

import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.sifi.R
import com.example.sifi.databinding.FragmentJobBinding

class JobFragment : Fragment() {

    private lateinit var jobList: List<TextView>
    override fun onStop() {
        super.onStop()
        Log.d("daeYoung", "Job 프라그먼트 onStop() 호출")

        val mainActivity = activity as ProfileSettingActivity
        // 나중에 아래 코드 없애고 선택 안되면 다음 버튼 못 누르게 변경
        var selectJob: String = "선택 안됨"
        kotlin.runCatching { jobList.filter { it.isSelected }[0] }
            .onSuccess {
                selectJob = it.text.toString()
            }.onFailure {
                Log.d("daeYoung", "오류 메시지: ${it.message}")
            }
        mainActivity.receiveData(this, mapOf("job" to selectJob))
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentJobBinding.inflate(inflater, container, false)
        jobList = listOf(
            binding.job1,
            binding.job2,
            binding.job3,
            binding.job4,
            binding.job5,
            binding.job6,
            binding.job7,
            binding.job8,
            binding.job9,
            binding.job10,
            binding.job11
        )

        jobList.forEach { textView ->
            val remainList = jobList.filter { it != textView }
            Log.d("daeYoung", "나머지 리스트: ${remainList}")

            textView.setOnClickListener {
                it.isSelected = !(it.isSelected)
                remainList.forEach { it.isSelected = false }
                Log.d("daeYoung", "선택된 뷰: ${(it as TextView).text}")
            }
        }

        return binding.root
    }

}