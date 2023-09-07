package com.example.makeyourbody.view.maketrainingmenu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.makeyourbody.CommonFormatter
import com.example.makeyourbody.NiftyCloudApiClient
import com.example.makeyourbody.view.dialog.DatePickerFragment
import com.example.makeyourbody.data.TrainingItem
import com.example.makeyourbody.databinding.FragmentMakeTrainingBinding
import com.example.makeyourbody.view.maketrainingmenu.edittraininglist.EditTrainingListAdapter
import com.example.makeyourbody.view.traininglist.TrainingListFragment


class MakeTrainingFragment : Fragment() {

    private var _binding: FragmentMakeTrainingBinding? = null
    private val binding get() = _binding!!

    private val editTrainingListViewModel: EditTrainingListViewModel by activityViewModels()

    private val onItemClick: (TrainingItem) -> Unit = { trainingItem ->
        editTrainingListViewModel.deleteSelectedItems(trainingItem)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //日付のダイアログ表示
        binding.menuDateBtn.setOnClickListener {

            DatePickerFragment().show(childFragmentManager, DatePickerFragment::class.java.name)

            //日付ダイアログでOK押下の際DatePickerFragmentからの値受け取り
            childFragmentManager.setFragmentResultListener(
                "request_key",
                viewLifecycleOwner
            ) { _, result: Bundle ->
                binding.menuDateEdit.setText(result.getString("date_picker_value"))
            }
        }

        //トレーニング種目選択リストダイアログ表示
        binding.menuItemSelectBtn.setOnClickListener {
            Log.d("---MakeTrainingFragment---", "種目選択ボタン押下")
            editTrainingListViewModel.setMenuDate(binding.menuDateEdit.text.toString())
            editTrainingListViewModel.setTargetUser(binding.menuTargetEdit.text.toString())
            TrainingListFragment().show(childFragmentManager, "fragment_list_training_item")
        }

        editTrainingListViewModel.selectedItems.observe(viewLifecycleOwner) {
            Log.d("---MakeTrainingFragment---", "makeTrainingViewModel監視")
            binding.menuSelectedItem.adapter = it?.let {
                EditTrainingListAdapter(it.toList(), onItemClick, editTrainingListViewModel)
            }
        }

        //メニュー登録ボタン押下
        binding.menuSaveBtn.setOnClickListener {

            //Dateに変換
            val date = CommonFormatter().dateConvert(binding.menuDateEdit.text.toString())

            NiftyCloudApiClient().saveMenuObject(
                editTrainingListViewModel.selectedItems.value.toString(),
                date, binding.menuTargetEdit.text.toString()
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("---MakeTrainingFragment---", "onCreateView()")
        _binding = FragmentMakeTrainingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        Log.d("---TMakeTrainingFragment---", "onDestroyView()")
        super.onDestroyView()
        _binding = null
    }

}