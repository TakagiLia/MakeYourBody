package com.takagi.makeyourbody.view.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.takagi.makeyourbody.view.dialog.DatePickerFragment
import com.takagi.makeyourbody.NiftyCloudApiClient
import com.takagi.makeyourbody.R
import com.takagi.makeyourbody.data.TrainingMenu
import com.takagi.makeyourbody.databinding.FragmentScheduleBinding
import com.takagi.makeyourbody.view.schedule.schedulelist.ScheduleListAdapter
import com.takagi.makeyourbody.view.menudetails.MenuDetailsViewModel

class ScheduleFragment : Fragment() {

    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!

    private var menuList : List<TrainingMenu> = emptyList()

    private val trainingMenuViewModel: MenuDetailsViewModel by activityViewModels()

    private val onDetailBtnClick: (TrainingMenu) -> Unit = { trainingMenu ->
        trainingMenuViewModel.setMenu(trainingMenu)
        findNavController().navigate(R.id.action_schedule_to_details)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //スケジュール表示(初期)                                                                                                                                                                                                                                                                                        リスト設定
        menuList = NiftyCloudApiClient().getTrainingMenu()
        binding.scheduleList.adapter = ScheduleListAdapter(menuList, onDetailBtnClick)

        //日付絞り込み解除ボタン
        binding.scheduleDateBtn.setOnClickListener {
            binding.scheduleDateEdit.text = ""
            menuList = NiftyCloudApiClient().getTrainingMenu()
            binding.scheduleList.adapter = ScheduleListAdapter(menuList, onDetailBtnClick)
        }

        //Editエリアタップで日付ダイアログ表示
        binding.scheduleDateEdit.setOnClickListener {

            //日付のダイアログ表示
            DatePickerFragment().show(childFragmentManager, DatePickerFragment::class.java.name)

            //日付ダイアログでOK押下の際DatePickerFragmentからの値受け取り
            childFragmentManager.setFragmentResultListener(
                "request_key",
                viewLifecycleOwner
            ) { _, result: Bundle ->
                binding.scheduleDateEdit.text = result.getString("date_picker_value")

                val resultSearch = binding.scheduleDateEdit.text.toString().let {
                    NiftyCloudApiClient().searchTrainingMenu(it)
                }
                binding.scheduleList.adapter = ScheduleListAdapter(resultSearch, onDetailBtnClick)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}