package com.example.android_assignment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.android_assignment.fragment.GridFragment
import com.example.android_assignment.fragment.LinearFragment
import com.example.android_assignment.fragment.ProfileFragment

/**
 * Created By kimdahyee
 * on 11월 21일, 2020
 */

class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> ProfileFragment()  //index가 0일때
            1 -> LinearFragment()
            else -> GridFragment()
        }
    }

    override fun getCount() = 3
}