package com.example.makeyourbody.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.makeyourbody.DatePickerFragment
import com.example.makeyourbody.NiftyCloudApiClient
import com.example.makeyourbody.R
import com.example.makeyourbody.data.TrainingMenu
import com.example.makeyourbody.databinding.FragmentScheduleBinding
import com.example.makeyourbody.schedule.schedulelist.ScheduleListAdapter
import com.example.makeyourbody.view.menudetails.MenuDetailsViewModel

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

        //日付ボタン
        binding.scheduleDateBtn.setOnClickListener {
            //日付のダイアログ表示
            DatePickerFragment().show(childFragmentManager, DatePickerFragment::class.java.name)

            //日付ダイアログでOK押下の際DatePickerFragmentからの値受け取り
            childFragmentManager.setFragmentResultListener(
                "request_key",
                viewLifecycleOwner
            ) { _, result: Bundle ->
                binding.scheduleDateEdit.setText(result.getString("date_picker_value"))

                var resultSearch =  binding.scheduleDateEdit.text.toString()?.let{
                    NiftyCloudApiClient().searchTrainingMenu(it)
                } ?: NiftyCloudApiClient().getTrainingMenu()
                binding.scheduleList.adapter = ScheduleListAdapter(resultSearch,onDetailBtnClick)
            }
        }

        //スケジュール表示(初期)                                                                                                                                                                                                                                                                                        リスト設定
        menuList = NiftyCloudApiClient().getTrainingMenu()
        binding.scheduleList.adapter = ScheduleListAdapter(menuList,onDetailBtnClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}