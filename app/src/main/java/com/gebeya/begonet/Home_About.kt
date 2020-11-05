package com.gebeya.begonet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gebeya.begonet.fragments.AboutFragment
import com.gebeya.begonet.fragments.HomeFragment
import com.gebeya.begonet.fragments.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_home__about.*

class Home_About : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home__about)

        setupTabs()
    }


    private fun setupTabs(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(), "Home")
        adapter.addFragment(AboutFragment(), "About Us")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }
}