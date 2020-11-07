package com.gebeya.begonet.ui.main

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.gebeya.begonet.base.BaseFragment
import com.gebeya.begonet.R
import com.google.firebase.auth.FirebaseAuth

class MainFragment : BaseFragment() {
    override val layoutId: Int
        get() = R.layout.fragment_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binds()
    }

    private fun binds() {
        if(FirebaseAuth.getInstance().currentUser == null){
            findNavController().navigate(R.id.actionMainFragmentToAuthFragment)
        } else {
            findNavController().navigate(R.id.actionMainFragmentToSelectorFragment)
        }
    }


}