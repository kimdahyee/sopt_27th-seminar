package com.example.android_assignment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.android_assignment.R
import com.example.android_assignment.adapter.ProfilePagerAdapter
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private lateinit var profilePagerAdapter: ProfilePagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profilePagerAdapter = ProfilePagerAdapter(childFragmentManager)
        profilePagerAdapter.fragment = listOf(
            InfoFragment(),
            OtherFragment()
        )
        viewPager_profile.adapter = profilePagerAdapter

        tabLayout_profile.setupWithViewPager(viewPager_profile)
        tabLayout_profile.apply {
            getTabAt(0)?.text = "INFO"
            getTabAt(1)?.text = "OTHER"
        }

    }

}