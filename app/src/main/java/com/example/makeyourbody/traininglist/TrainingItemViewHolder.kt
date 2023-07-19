package com.example.makeyourbody.traininglist

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.makeyourbody.R

class TrainingItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val itemName = itemView.findViewById<TextView>(R.id.training_date)

    val itemContent = itemView.findViewById<TextView>(R.id.training_target)

}