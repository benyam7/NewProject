package com.gebeya.begonet.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gebeya.begonet.base.ProjectStatus
import kotlinx.android.synthetic.main.item_project_status.view.*

class ProjectStatusViewHolder(private val view: View) : RecyclerView.ViewHolder(view){

    fun bind(status: ProjectStatus) {
        view.apply {
            statusText.text = status.status
            Glide.with(context)
                .load(status.imageUrl)
                .into(statusImage)
        }
    }
}