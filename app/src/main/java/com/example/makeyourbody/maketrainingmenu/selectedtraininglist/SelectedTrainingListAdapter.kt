package com.example.makeyourbody.maketrainingmenu.selectedtraininglist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.makeyourbody.R
import com.example.makeyourbody.data.TrainingItem

class SelectedTrainingListAdapter(
    private val trainingItems: List<TrainingItem>
) : RecyclerView.Adapter<SelectedTrainingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedTrainingViewHolder =
        SelectedTrainingViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_list_selected_training,parent, false)
        )

    override fun onBindViewHolder(holder: SelectedTrainingViewHolder, position: Int) {
        val trainingItem = trainingItems[position]
        holder.itemName.text = trainingItem.name
    }

    override fun getItemCount(): Int = trainingItems.size
}