package com.gebeya.begonet.ui.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.auth.AuthUI
import com.gebeya.begonet.R
import com.gebeya.begonet.base.BaseFragment
import com.gebeya.begonet.base.ProjectStatus
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment() {
    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val statusAdapter = ProjectStatusAdapter(listOf(
            ProjectStatus(
                "Completed", R.drawable.kid,
                ),ProjectStatus(
                "Semi Completed", R.drawable.kid2,
            ),ProjectStatus(
                "In Progress", R.drawable.kid_five,
            )
        ))
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        projectStatusRecycler.adapter = statusAdapter

    }

    private fun logOut() {
        AuthUI.getInstance()
            .signOut(requireContext())
    }
}