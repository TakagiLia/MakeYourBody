package com.example.makeyourbody.view.menudetails

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.makeyourbody.R
import com.example.makeyourbody.data.TrainingItem

class MenuDetailsListAdapter (
    private val trainingManus: List<TrainingItem>,
//    private val onClickInfoBtn: (TrainingItem)-> Unit
) : RecyclerView.Adapter<MenuDetailsListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuDetailsListViewHolder =
        MenuDetailsListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_list_menu_details,parent, false)
        )

    override fun onBindViewHolder(holder: MenuDetailsListViewHolder, position: Int) {
        val trainingMenu = trainingManus[position]
        holder.menuName.text = trainingMenu.name

//        holder.infoBtn.setOnClickListener {
//            Log.d("--Click List InfoBtn---",trainingManus[position].toString())
//            onClickInfoBtn(trainingManus[position])
//        }
    }

    override fun getItemCount(): Int = trainingManus.size


}