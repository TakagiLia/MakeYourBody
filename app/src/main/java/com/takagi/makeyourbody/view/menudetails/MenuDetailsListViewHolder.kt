package com.takagi.makeyourbody.view.menudetails

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.takagi.makeyourbody.R

class MenuDetailsListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val menuName: TextView = itemView.findViewById(R.id.menu_details_list_name)

    val infoBtn: ImageButton = itemView.findViewById(R.id.menu_details_list_btn)

}