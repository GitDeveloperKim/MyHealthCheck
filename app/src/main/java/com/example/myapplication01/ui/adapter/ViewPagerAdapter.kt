package com.example.myapplication01.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication01.ui.fragment.DietEnrollmentFragment
import com.example.myapplication01.ui.fragment.HealthEnrollmentFragment

private const val NUM_TABS = 2

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle:Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return HealthEnrollmentFragment()
            1 -> return DietEnrollmentFragment()
        }
        return HealthEnrollmentFragment()
    }


}