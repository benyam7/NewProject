package com.gebeya.begonet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gebeya.begonet.framework.base.BaseActivity
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_landing_page.*

class EditProfile : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        backArrow.setOnClickListener {

            val intent = Intent(this, LandingPage::class.java)
            startActivity(intent)
        }
    }
}