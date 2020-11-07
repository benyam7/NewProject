//package com.gebeya.begonet
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import kotlinx.android.synthetic.main.activity_landing_page.*
//import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.android.synthetic.main.activity_setting.*
//
//class LandingPage : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_landing_page)
//
//        home.setOnClickListener {
//
//            val intent = Intent(this, Home_About::class.java)
//            startActivity(intent)
//        }
//
//        setting.setOnClickListener {
//
//            val intent = Intent(this, Setting::class.java)
//            startActivity(intent)
//        }
//
//        settingText.setOnClickListener {
//
//            val intent = Intent(this, Setting::class.java)
//            startActivity(intent)
//        }
//
//        about.setOnClickListener {
//
//            val intent = Intent(this, AboutUs::class.java)
//            startActivity(intent)
//        }
//
//        aboutText.setOnClickListener {
//
//            val intent = Intent(this, AboutUs::class.java)
//            startActivity(intent)
//        }
//
//        editText.setOnClickListener {
//
//            val intent = Intent(this, EditProfile::class.java)
//            startActivity(intent)
//        }
//
//        home.setOnClickListener {
//
//            val intent = Intent(this, Home::class.java)
//            startActivity(intent)
//        }
//        homeText.setOnClickListener {
//
//            val intent = Intent(this, Home::class.java)
//            startActivity(intent)
//        }
//    }
//
//}