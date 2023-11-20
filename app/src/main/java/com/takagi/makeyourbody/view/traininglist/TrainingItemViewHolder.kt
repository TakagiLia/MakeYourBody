package com.takagi.makeyourbody.view.traininglist

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.takagi.makeyourbody.R

class TrainingItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val itemName: TextView = itemView.findViewById<TextView>(R.id.training_date)

    val itemContent: TextView = itemView.findViewById<TextView>(R.id.training_target)

}