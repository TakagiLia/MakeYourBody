package com.takagi.makeyourbody.view.schedule.schedulelist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.takagi.makeyourbody.R
import com.takagi.makeyourbody.data.TrainingMenu


class ScheduleListAdapter(
    private val menuList: List<TrainingMenu>,
    private val onItemClick: (TrainingMenu) -> Unit
) :
    RecyclerView.Adapter<ScheduleListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleListViewHolder =
        ScheduleListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_list_schedule, parent, false)
        )

    override fun onBindViewHolder(holder: ScheduleListViewHolder, position: Int) {
        val menu = menuList[position]

        holder.trainingDate.text = menu.menuDate
        holder.trainingTarget.text = menu.menuTarget

        holder.trainingDetailBtn.setOnClickListener {
            Log.d("---click trainingDetailBtn---", menu.toString())
            onItemClick(menu)
        }
    }

    override fun getItemCount(): Int = menuList.size
}
