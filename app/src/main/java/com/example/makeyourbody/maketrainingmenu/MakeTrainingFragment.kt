package com.example.makeyourbody.maketrainingmenu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.makeyourbody.DatePickerFragment
import com.example.makeyourbody.databinding.FragmentMakeTrainingBinding
import com.example.makeyourbody.maketrainingmenu.selectedtraininglist.SelectedTrainingListAdapter
import com.example.makeyourbody.maketrainingmenu.traininglist.TrainingListFragment


class MakeTrainingFragment : Fragment() {

    private var _binding: FragmentMakeTrainingBinding? = null
    private val binding get() = _binding!!

    private val makeTrainingViewModel: MakeTrainingViewModel by activityViewModels()

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
            makeTrainingViewModel.setMenuDate(binding.menuDateEdit.text.toString())
            makeTrainingViewModel.setTargetUser(binding.menuTargetEdit.text.toString())
            TrainingListFragment().show(childFragmentManager,"fragment_list_training_item")
        }

        makeTrainingViewModel.selectedItems.observe(viewLifecycleOwner) {
            binding.menuSelectedItem2.adapter = it?.let{
                SelectedTrainingListAdapter(it.toList())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("[TopPageFragment]", "onCreateView()")
        _binding = FragmentMakeTrainingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        Log.d("[TopPageFragment]", "onDestroyView()")
        super.onDestroyView()
        _binding = null
    }

}