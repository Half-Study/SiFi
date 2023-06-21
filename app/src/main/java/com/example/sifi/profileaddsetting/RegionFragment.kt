package com.example.sifi.profileaddsetting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sifi.MainActivity
import com.example.sifi.R
import com.example.sifi.databinding.FragmentRegionBinding

class RegionFragment : Fragment() {
    override fun onStop() {
        super.onStop()
        Log.d("daeYoung", "지역 프라그먼트 onStop() 호출")
        val mainActivity = activity as ProfileSettingActivity
        mainActivity.receiveData(this, mapOf("region" to currentRegion) )
    }

    lateinit var binding: FragmentRegionBinding
    lateinit var numberPicker: NumberPicker
    lateinit var currentRegion: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegionBinding.inflate(inflater, container, false)
        numberPicker = binding.numberPicker

        setupNumberPickerForStringValues()



        return binding.root
    }

    private fun setupNumberPickerForStringValues() {
        val list = getRegionList()
        numberPicker.also {
            currentRegion = list[0]
            it.minValue = 0
            it.maxValue = list.size - 1
            it.displayedValues = list
            it.wrapSelectorWheel = true
            it.setOnValueChangedListener { picker, oldVal, newVal ->
                currentRegion = list[newVal]
//                Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getRegionList(): Array<String> = arrayOf(
        getString(R.string.activityRegion_fragment_region1),
        getString(R.string.activityRegion_fragment_region2),
        getString(R.string.activityRegion_fragment_region3),
        getString(R.string.activityRegion_fragment_region4),
        getString(R.string.activityRegion_fragment_region5),
        getString(R.string.activityRegion_fragment_region6),
        getString(R.string.activityRegion_fragment_region7),
        getString(R.string.activityRegion_fragment_region8),
        getString(R.string.activityRegion_fragment_region9),
        getString(R.string.activityRegion_fragment_region10),
        getString(R.string.activityRegion_fragment_region11),
        getString(R.string.activityRegion_fragment_region12),
        getString(R.string.activityRegion_fragment_region13),
        getString(R.string.activityRegion_fragment_region14),
        getString(R.string.activityRegion_fragment_region15),
        getString(R.string.activityRegion_fragment_region16),
        getString(R.string.activityRegion_fragment_region17),
        getString(R.string.activityRegion_fragment_region18),
        getString(R.string.activityRegion_fragment_region19)
    )


}