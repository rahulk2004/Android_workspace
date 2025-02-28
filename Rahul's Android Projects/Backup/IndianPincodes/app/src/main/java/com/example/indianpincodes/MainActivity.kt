package com.example.indianpincodes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.indianpincodes.fragments.QuickSearchFragment
import com.example.indianpincodes.fragments.SearchByAreaFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewPager = findViewById(R.id.viewpager)
        setupViewPager()

        tabLayout = findViewById(R.id.tab)
        tabLayout.setupWithViewPager(viewPager)

    }



    private fun setupViewPager() {
        val adapter = FragmentPagerAdapter(supportFragmentManager)
        adapter.addFragment(QuickSearchFragment(), "Quick Search")
        adapter.addFragment(SearchByAreaFragment(), "Search by Area")
        adapter.addFragment(SavedPinsFragment(), "Saved Pins")
        viewPager.adapter = adapter
    }
}
