package com.gebeya.begonet.ui.settings

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.gebeya.begonet.R
import com.gebeya.begonet.base.BaseFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment: BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_settings

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settingsLogout.setOnClickListener{
            AuthUI.getInstance()
                .signOut(requireContext())
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                       Toast.makeText(requireContext(),"Singed out ", Toast.LENGTH_LONG).show()
                    }
                }


        }
    }
}