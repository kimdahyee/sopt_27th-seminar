package com.example.android_assignment.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.android_assignment.R
import com.example.android_assignment.adapter.MainPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager_main.adapter = MainPagerAdapter(supportFragmentManager)
        viewPager_main.offscreenPageLimit = 2

        viewPager_main.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            // viewPager의 이동 -> 하단 탭의 체크 상태 변화
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                // navigation 메뉴 아이템 체크
                main_bottomNavigation.menu.getItem(position).isChecked = true
            }
        })

        main_bottomNavigation.setOnNavigationItemSelectedListener {
            // 하단 탭의 체크 이벤트 -> 해당하는 페이지로 이동
            var index by Delegates.notNull<Int>()
            when (it.itemId) {
                R.id.nav_profile -> index = 0
                R.id.nav_linear -> index = 1
                R.id.nav_grid -> index = 2
            }
            viewPager_main.currentItem = index
            true
        }
    }

}