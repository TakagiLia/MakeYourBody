package com.example.makeyourbody.view.maketrainingmenu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
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

        binding.apply {
            editTrainingListViewModel.also { viewModel ->
                //日付のダイアログ表示
                menuDateBtn.setOnClickListener {

                    DatePickerFragment().show(
                        childFragmentManager,
                        DatePickerFragment::class.java.name
                    )

                    //日付ダイアログでOK押下の際DatePickerFragmentからの値受け取り
                    childFragmentManager.setFragmentResultListener(
                        "request_key",
                        viewLifecycleOwner
                    ) { _, result: Bundle ->
                        result.getString("date_picker_value")
                            ?.let { it1 ->
                                viewModel.setMenuDate(it1)
                                menuDateEdit.setText(it1)
                            }
                    }
                }

                //対象ユーザをEditTrainingListViewModelのTargetUserにセット
                menuTargetEdit.addTextChangedListener {
                    Log.d("---menuTargetEdit---", menuTargetEdit.text.toString())
                    viewModel.setTargetUser(menuTargetEdit.text.toString())
                }

                //トレーニング種目選択リストダイアログ表示
                menuItemSelectBtn.setOnClickListener {
                    Log.d("---MakeTrainingFragment---", "種目選択ボタン押下")
                    TrainingListFragment().show(childFragmentManager, "fragment_list_training_item")
                }

                //selectedItems監視
                viewModel.selectedItems.observe(viewLifecycleOwner) {
                    Log.d("---MakeTrainingFragment---", "selectedItems監視")
                    menuSelectedItem.adapter = it?.let {
                        EditTrainingListAdapter(it.toList(), onItemClick, editTrainingListViewModel)
                    }

                    //保存ボタンの活性非活性
                    menuSaveBtn.visibility =
                        if (!viewModel.inputCheckTargetUser() && !viewModel.inputCheckDate() &&
                            !viewModel.inputCheckSelectedItems()
                        ) {
                            View.VISIBLE
                        } else {
                            View.INVISIBLE
                        }
                }

                //menuTargetUserの監視
                viewModel.menuTargetUser.observe(viewLifecycleOwner) {
                    Log.d("---MakeTrainingFragment---", "menuTargetUser監視")
                    //保存ボタンの活性非活性
                    menuSaveBtn.visibility =
                        if (!viewModel.inputCheckTargetUser() && !viewModel.inputCheckDate() &&
                            !viewModel.inputCheckSelectedItems()
                        ) {
                            View.VISIBLE
                        } else {
                            View.INVISIBLE
                        }
                }

                //menuDateの監視
                viewModel.menuDate.observe(viewLifecycleOwner) {
                    Log.d("---MakeTrainingFragment---", "menuDate監視")
                    //保存ボタンの活性非活性
                    menuSaveBtn.visibility =
                        if (!viewModel.inputCheckTargetUser() && !viewModel.inputCheckDate() &&
                            !viewModel.inputCheckSelectedItems()
                        ) {
                            View.VISIBLE
                        } else {
                            View.INVISIBLE
                        }
                }

                //メニュー登録ボタン押下
                menuSaveBtn.setOnClickListener {

                    //Dateに変換
                    val date =
                        CommonFormatter().dateConvert(viewModel.menuDate.value.toString())

                    NiftyCloudApiClient().saveMenuObject(
                        viewModel.selectedItems.value,
                        date, viewModel.menuTargetUser.value.toString()
                    )
                }
            }
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
        editTrainingListViewModel.clearSelectedItems()
    }

}