package com.example.sifi.profileaddsetting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sifi.R
import com.example.sifi.databinding.FragmentRegionBinding

class RegionFragment : Fragment() /*, ProfileSettingActivity.onBackPressedListener */{
    //    lateinit var adapter: RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>
    lateinit var binding: FragmentRegionBinding

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
        setupNumberPickerForStringValues()
        return binding.root
    }

    private fun setupNumberPickerForStringValues() {
        val numberPicker = binding.numberPicker
        val list = getRegionList()
        numberPicker.also {
            it.minValue = 0
            it.maxValue = list.size - 1
            it.displayedValues = list
            it.wrapSelectorWheel = true
//            it.setOnValueChangedListener { picker, oldVal, newVal ->
//                val text = "Changed from " + list[oldVal] + " to " + list[newVal]
//                Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
//            }
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

//    override fun onBackPressed() {
//        Log.d("daeYoung", "지역 프라그먼트 뒤로가기 실행")
//        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
//    }
}