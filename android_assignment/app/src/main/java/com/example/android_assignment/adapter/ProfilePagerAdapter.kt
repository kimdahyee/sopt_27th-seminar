package com.example.android_assignment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.android_assignment.fragment.*
import java.lang.IllegalStateException

/**
 * Created By kimdahyee
 * on 11월 30일, 2020
 */
 
class ProfilePagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    var fragment = listOf<Fragment>()

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> InfoFragment()  //index가 0일때
            1 -> OtherFragment()
            else -> throw IllegalStateException("Unexpected position")
        }
    }

    override fun getCount() = fragment.size
}