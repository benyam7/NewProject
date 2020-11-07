package com.gebeya.begonet.ui.selector

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.addisfortune.printversion.base.ui.BaseFragment
import com.firebase.ui.auth.AuthUI
import com.gebeya.begonet.R
import com.gebeya.begonet.framework.util.log
import kotlinx.android.synthetic.main.fragment_selector.*

class SelectorFragment :BaseFragment() {
    override val layoutId: Int
        get() = R.layout.fragment_selector

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binds()
    }

    private fun binds() {
        logout.setOnClickListener{
            AuthUI.getInstance()
                .signOut(requireContext())
            findNavController().navigate(R.id.actionSelectorToAuth)
        }
    }

}