package com.gebeya.begonet.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gebeya.begonet.R
import com.gebeya.begonet.base.ProjectStatus

class ProjectStatusAdapter(private val projectStatus: List<ProjectStatus>): RecyclerView.Adapter<ProjectStatusViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectStatusViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_project_status, parent, false)
        return ProjectStatusViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectStatusViewHolder, position: Int) {
        holder.bind(projectStatus[position])
    }

    override fun getItemCount() = projectStatus.size
}