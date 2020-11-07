package com.gebeya.begonet.ui.main

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.gebeya.begonet.R
import com.gebeya.begonet.base.BaseFragment
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment() {
    override val layoutId: Int
        get() = R.layout.fragment_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomAppNavigationView.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
        val navController =
            Navigation.findNavController(requireActivity(), R.id.buttomNavHostContainerFragment)
        NavigationUI.setupWithNavController(bottomAppNavigationView, navController)
    }
}