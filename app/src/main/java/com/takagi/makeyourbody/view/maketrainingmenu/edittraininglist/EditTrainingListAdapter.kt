package com.takagi.makeyourbody.view.maketrainingmenu.edittraininglist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.takagi.makeyourbody.R
import com.takagi.makeyourbody.data.TrainingItem
import com.takagi.makeyourbody.view.maketrainingmenu.EditTrainingListViewModel

class EditTrainingListAdapter(
    private val trainingItems: List<TrainingItem>,
    private val deleteSelectedItems: (TrainingItem) -> Unit,
    private val viewModel: EditTrainingListViewModel
) : RecyclerView.Adapter<EditTrainingListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditTrainingListViewHolder =
        EditTrainingListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_list_selected_training,parent, false)
        )

    override fun onBindViewHolder(holder: EditTrainingListViewHolder, position: Int) {
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