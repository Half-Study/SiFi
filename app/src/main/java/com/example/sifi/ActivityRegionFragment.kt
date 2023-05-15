package com.example.sifi

import android.content.AbstractThreadedSyncAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.sifi.databinding.FragmentActivityRegionBinding

class ActivityRegionFragment : Fragment() {
    //    lateinit var adapter: RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>
    lateinit var binding: FragmentActivityRegionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentActivityRegionBinding.inflate(inflater, container, false)
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
            it.setOnValueChangedListener { picker, oldVal, newVal ->
                val text = "Changed from " + list[oldVal] + " to " + list[newVal]
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
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