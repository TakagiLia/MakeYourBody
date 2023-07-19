package com.example.makeyourbody.maketrainingmenu.traininglist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.makeyourbody.R
import com.example.makeyourbody.data.TrainingItem

class TrainingListAdapter(
    private val trainingItems: List<TrainingItem>,
    private val onItemClick: (TrainingItem) -> Unit
) : RecyclerView.Adapter<TrainingItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingItemViewHolder =
        TrainingItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_list_training_item, parent, false)
        )

    //ビューにデータ割り当て、リスト項目を生成
    override fun onBindViewHolder(holder: TrainingItemViewHolder, position: Int) {
        val trainingItem = trainingItems[position]

        holder.itemName.text = trainingItem.name
        holder.itemContent.text = trainingItem.detail

        holder.itemView.setOnClickListener {
            Log.d("@@TrainingItem","btnClick")
            onItemClick(trainingItem)
        }

    }

    override fun getItemCount(): Int = trainingItems.size

}