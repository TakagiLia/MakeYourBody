package com.example.makeyourbody.view.menudetails

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.makeyourbody.R

class MenuDetailsListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val menuName = itemView.findViewById<TextView>(R.id.menu_details_list_name)

    val infoBtn = itemView.findViewById<ImageButton>(R.id.menu_details_list_btn)

}