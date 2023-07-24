package com.example.makeyourbody.view.menudetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.makeyourbody.NiftyCloudApiClient
import com.example.makeyourbody.R
import com.example.makeyourbody.data.TrainingItem
import com.example.makeyourbody.databinding.FragmentMenuDetailsBinding
import com.example.makeyourbody.view.exercisedetails.ExerciseDetailsViewModel

class MenuDetailsFragment :Fragment() {

    private var _binding: FragmentMenuDetailsBinding? = null
    private val binding get() = _binding!!

    //トレーニングメニュー詳細情報保持用
    private val menuDetailsViewModel: MenuDetailsViewModel by activityViewModels()

    //トレーニングアイテム詳細ページ表示用
    private val exerciseDetailsViewModel: ExerciseDetailsViewModel by activityViewModels()

    //リストアイテム詳細ページ遷移
    private val onClickInfoBtn: (TrainingItem)-> Unit = { trainingItem ->
        Log.d("--onClickInfoBtn--","詳細ボタン押下")
        exerciseDetailsViewModel.setItem(trainingItem)
        findNavController().navigate(R.id.action_menu_details_to_exercise_details_screen)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fixingList =
            NiftyCloudApiClient().trainingItemFixList(menuDetailsViewModel.menu.value?.menuContent.toString())
        binding.apply {
            menuDetailsDate.setText(menuDetailsViewModel.menu.value?.menuDate)
            menuDetailsTarget.setText(menuDetailsViewModel.menu.value?.menuTarget)
            var menuDetailsItemList = NiftyCloudApiClient().getTrainingItemBindingMenu(fixingList) ?: emptyList()
            menuDetailsList.adapter = MenuDetailsListAdapter(menuDetailsItemList,onClickInfoBtn)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}