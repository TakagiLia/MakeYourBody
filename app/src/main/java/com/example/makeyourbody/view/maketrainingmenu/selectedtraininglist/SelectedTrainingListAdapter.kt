package com.example.makeyourbody.view.maketrainingmenu.selectedtraininglist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.makeyourbody.R
import com.example.makeyourbody.data.TrainingItem
import com.example.makeyourbody.view.maketrainingmenu.EditTrainingListViewModel

class SelectedTrainingListAdapter(
    private val trainingItems: List<TrainingItem>,
    private val deleteSelectedItems: (TrainingItem) -> Unit,
    private val viewModel: EditTrainingListViewModel
) : RecyclerView.Adapter<SelectedTrainingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedTrainingViewHolder =
        SelectedTrainingViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_list_selected_training,parent, false)
        )

    override fun onBindViewHolder(holder: SelectedTrainingViewHolder, position: Int) {
        Log.d("---SelectedTrainingListAdapter---","Selectedリストの設定")
        val trainingItem = trainingItems[position]
        holder.itemName.text = trainingItem.name

        //選択したリストを削除するアイコンボタン押下
        holder.deleteBtn.setOnClickListener {
            Log.d("---ListItem Delete---",trainingItem.toString())
            viewModel.deleteSelectedItems(trainingItem)
        }
    }

    override fun getItemCount(): Int = trainingItems.size
}