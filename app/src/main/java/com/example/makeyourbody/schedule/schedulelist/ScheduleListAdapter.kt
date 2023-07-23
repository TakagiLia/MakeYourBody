package com.example.makeyourbody.schedule.schedulelist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.makeyourbody.R
import com.example.makeyourbody.data.TrainingMenu


class ScheduleListAdapter(
    private val menuList: List<TrainingMenu>,
    private val view: View,
    //private val onItemClick: (TrainingMenu) -> Unit
) :
    RecyclerView.Adapter<ScheduleListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleListViewHolder =
        ScheduleListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_list_schedule, parent, false)
        )

    override fun onBindViewHolder(holder: ScheduleListViewHolder, position: Int) {
        val menu = menuList[position]

        holder.trainingdate.text = menu.menuDate
        holder.training_target.text = menu.menuTarget

//        holder.trainingDetailBtn.setOnClickListener {
//            Log.d("■スケージュールリストのボタン", menu.toString())
//            Log.d("■スケージュールリストのボタン", "スケージュールリストのボタン")
//            onItemClick(menu)
//        }

    }

    override fun getItemCount(): Int = menuList.size
}
