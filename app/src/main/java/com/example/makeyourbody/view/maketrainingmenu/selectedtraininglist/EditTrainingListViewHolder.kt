package com.example.makeyourbody.view.maketrainingmenu.selectedtraininglist

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.makeyourbody.R

class EditTrainingListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val itemName = itemView.findViewById<TextView>(R.id.selected_item_name)

    val  deleteBtn = itemView.findViewById<ImageButton>(R.id.selected_delete_btn)
}