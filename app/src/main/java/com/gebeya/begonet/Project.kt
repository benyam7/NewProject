package com.gebeya.begonet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gebeya.begonet.framework.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_project.*

class Project : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project)

        backHome.setOnClickListener {

            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        threeLines.setOnClickListener {

            val intent = Intent(this, LandingPage::class.java)
            startActivity(intent)
        }
    }
}