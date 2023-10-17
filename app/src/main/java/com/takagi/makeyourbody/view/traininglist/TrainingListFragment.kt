package com.takagi.makeyourbody.view.traininglist

import android.app.ActionBar.LayoutParams
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.SearchView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.takagi.makeyourbody.NiftyCloudApiClient
import com.takagi.makeyourbody.R
import com.takagi.makeyourbody.data.TrainingItem
import com.takagi.makeyourbody.databinding.FragmentTrainingListBinding
import com.takagi.makeyourbody.view.maketrainingmenu.EditTrainingListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrainingListFragment : DialogFragment() {

    private var _binding: FragmentTrainingListBinding? = null
    private val binding get() = _binding!!

    private var trainingItems: List<TrainingItem> = emptyList()

    private val editTrainingListViewModel: EditTrainingListViewModel by activityViewModels()

    //トレーニングアイテム詳細ページ表示用
    private val trainingItemViewModel: TrainingItemViewModel by activityViewModels()

    private val onItemClick: (TrainingItem) -> Unit = { trainingItem ->
        editTrainingListViewModel.setSelectedItem(trainingItem)
        trainingItemViewModel.setItem(trainingItem)
        dismiss()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //種目マスタ取得、リスト表示
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            trainingItems = NiftyCloudApiClient().getTrainingItemList()
            binding.selectItemList.adapter = TrainingListAdapter(trainingItems, onItemClick)
        }

        //種目マスタの検索
        binding.selectItemSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val filterList = trainingItems.filter { it.name == query }
                binding.selectItemList.adapter = TrainingListAdapter(filterList, onItemClick)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    binding.selectItemList.adapter = TrainingListAdapter(trainingItems, onItemClick)
                }
                return false
            }
        })
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setContentView(R.layout.fragment_list_training_item)
        dialog.window?.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT)
        isCancelable = false
        dialog.setCanceledOnTouchOutside(true)
        dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)

        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrainingListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

