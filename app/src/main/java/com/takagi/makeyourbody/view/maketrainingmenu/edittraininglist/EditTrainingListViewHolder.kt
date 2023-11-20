package com.takagi.makeyourbody.view.maketrainingmenu.edittraininglist

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.takagi.makeyourbody.R

class EditTrainingListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val itemName: TextView = itemView.findViewById(R.id.selected_item_name)

    val  deleteBtn: ImageButton = itemView.findViewById(R.id.selected_delete_btn)
}