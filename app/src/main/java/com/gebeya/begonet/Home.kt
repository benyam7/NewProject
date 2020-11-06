package com.gebeya.begonet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gebeya.begonet.framework.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.login

class Home : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        projectOne.setOnClickListener {

            val intent = Intent(this, Project::class.java)
            startActivity(intent)
        }
        threeLines.setOnClickListener {

            val intent = Intent(this, LandingPage::class.java)
            startActivity(intent)
        }
    }

    }
