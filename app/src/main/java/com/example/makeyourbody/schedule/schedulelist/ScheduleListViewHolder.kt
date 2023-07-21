package com.example.makeyourbody.schedule.schedulelist

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.makeyourbody.R

class ScheduleListViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

    val trainingdate = itemView.findViewById<TextView>(R.id.training_date)

    val training_target = itemView.findViewById<TextView>(R.id.training_target)

    val trainingDetailBtn = itemView.findViewById<Button>(R.id.training_details_btn)

}