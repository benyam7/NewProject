package com.gebeya.begonet

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.gebeya.begonet.fragments.AboutFragment
import com.gebeya.begonet.fragments.HomeFragment
import com.gebeya.begonet.fragments.adapters.ViewPagerAdapter
import com.gebeya.begonet.framework.base.BaseActivity
import kotlinx.android.synthetic.main.activity_log_in_page.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    internal lateinit var viewPager : ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       signUpButton.setOnClickListener {
          val intent =  Intent(this, SignUpPage::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {

           val intent = Intent(this, LogInPage::class.java)
            startActivity(intent)
        }

        skipText.setOnClickListener {

            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }



      /*  viewPager = findViewById<View>(R.id.viewPager) as ViewPager
        val adapter= ViewPageAdapter(this)
        viewPager.adapter = adapter */


    }


}