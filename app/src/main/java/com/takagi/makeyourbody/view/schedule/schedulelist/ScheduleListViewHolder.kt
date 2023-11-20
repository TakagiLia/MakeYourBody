package com.takagi.makeyourbody.view.schedule.schedulelist

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.takagi.makeyourbody.R

class ScheduleListViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

    val trainingDate: TextView = itemView.findViewById(R.id.training_date)

    val trainingTarget: TextView = itemView.findViewById(R.id.training_target)

    val trainingDetailBtn: Button = itemView.findViewById(R.id.training_details_btn)

}