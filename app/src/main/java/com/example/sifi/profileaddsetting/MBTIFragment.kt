package com.example.sifi.profileaddsetting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.sifi.databinding.FragmentMbtiBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch


class MBTIFragment : Fragment() {
//    lateinit var myViewModel: MyViewModel

    lateinit var buttonImageList: List<ImageButton>
    lateinit var textViewList: List<TextView>
    private val currentMBTI: MutableStateFlow<String> = MutableStateFlow("")

    override fun onStop() {
        super.onStop()
        Log.d("daeYoung", "MBTI 프라그먼트 onStop() 호출")
        val mainActivity = activity as ProfileSettingActivity
        val MBTIFragment = this
        CoroutineScope(Dispatchers.Default).launch {
            mainActivity.receiveData(MBTIFragment, mapOf("mbti" to (currentMBTI.first())))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMbtiBinding.inflate(inflater, container, false)

        // 뷰 모델 사용
//        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]


        buttonImageList = listOf(
            binding.ISTJ,
            binding.ISFJ,
            binding.INFJ,
            binding.INTJ,
            binding.ISTP,
            binding.ISFP,
            binding.INFP,
            binding.INTP,
            binding.ESTP,
            binding.ESFP,
            binding.ENFP,
            binding.ENTP,
            binding.ESTJ,
            binding.ESFJ,
            binding.ENFJ,
            binding.ENTJ
        )
        textViewList = listOf(
            binding.ISTJText,
            binding.ISFJText,
            binding.INFJText,
            binding.INTJText,
            binding.ISTPText,
            binding.ISFPText,
            binding.INFPText,
            binding.INTPText,
            binding.ESTPText,
            binding.ESFPText,
            binding.ENFPText,
            binding.ENTPText,
            binding.ESTJText,
            binding.ESFJText,
            binding.ENFJText,
            binding.ENTJText
        )

        buttonImageList.forEachIndexed { index, imageButton ->
            val remainList = buttonImageList.filter { it != imageButton }
            imageButton.setOnClickListener {
                it.isSelected = !it.isSelected
                textViewList[index].isSelected = !textViewList[index].isSelected

                with(viewLifecycleOwner) {
                    lifecycleScope.launch {
                        repeatOnLifecycle(Lifecycle.State.CREATED) {
                            if (it.isSelected) {
                                currentMBTI.emit(textViewList[index].text.toString())
//                        myViewModel.changeStateFlow(textViewList[index].text.toString())
                            } else {
                                currentMBTI.emit("")
//                        myViewModel.changeStateFlow("")
                            }

                            Log.d("daeYoung", "현재 MBTI: ${currentMBTI.firstOrNull()}")
                        }
                    }
                }

                textViewList.forEach {
                    if (it != textViewList[index]) {
                        it.isSelected = false
                    }
                }
                remainList.forEach { it.isSelected = false }
            }
        }

        return binding.root
    }

    fun isNext(): MutableStateFlow<String> = currentMBTI


}