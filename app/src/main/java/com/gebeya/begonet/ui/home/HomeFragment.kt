package com.gebeya.begonet.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.gebeya.begonet.R
import com.gebeya.begonet.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.math.log


class HomeFragment: BaseFragment () {
    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        logout.setOnClickListener{
//            logOut()
//        }
    }

    private fun logOut(){
        AuthUI.getInstance()
            .signOut(requireContext())

    }
}