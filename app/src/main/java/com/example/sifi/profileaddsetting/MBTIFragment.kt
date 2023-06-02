package com.example.sifi.profileaddsetting

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.LiveData
import com.example.sifi.R
import com.example.sifi.databinding.FragmentMbtiBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.math.log


class MBTIFragment : Fragment() {
    lateinit var buttonImageList: List<ImageButton>
    lateinit var textViewList: List<TextView>
    var currentMBTI: MutableStateFlow<String> = MutableStateFlow("")

    override fun onStop() {
        super.onStop()
        Log.d("daeYoung", "MBTI 프라그먼트 onStop() 호출")
        val mainActivity = activity as ProfileSettingActivity
//        mainActivity.receiveData(this, mapOf("mbti" to (currentMBTI. ?: "")) )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMbtiBinding.inflate(inflater, container, false)
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

//                currentMBTI = textViewList[index].text.toString()
                CoroutineScope(Dispatchers.Default).launch {
                    currentMBTI.emit(textViewList[index].text.toString())

                }

                textViewList.forEach {
                    if (it != textViewList[index]) { it.isSelected = false }
                }
                remainList.forEach { it.isSelected = false }
                Log.d("daeYoung", "현재 MBTI: ${currentMBTI}")
            }
        }

        return binding.root
    }

//    fun isNext():Flow<Boolean> = flow{
//        Log.d("daeYoung", "currentMBTI는 비어있는가?: ${currentMBTI.isNullOrBlank()}")
//        if (currentMBTI.isNullOrBlank()) {
//            emit(false)
//        }
//        else emit(true)
//    }

//    suspend fun isNext():Boolean {
//        Log.d("daeYoung", "currentMBTI는 비어있는가?: ${currentMBTI.firstOrNull()}")
//        var ret: Boolean = false
//        currentMBTI.collectLatest {
//            ret = it.isBlank()
//        }
//        return ret
//    }

    fun isNext(): MutableStateFlow<String> = currentMBTI


}