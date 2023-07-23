package com.example.makeyourbody.view.menudetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.makeyourbody.NiftyCloudApiClient
import com.example.makeyourbody.databinding.FragmentMenuDetailsBinding

class MenuDetailsFragment :Fragment() {

    private var _binding: FragmentMenuDetailsBinding? = null
    private val binding get() = _binding!!

    //トレーニングメニュー詳細情報保持用
    private val menuDetailsViewModel: MenuDetailsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fixingList = NiftyCloudApiClient().trainingItemFixList(menuDetailsViewModel.menu.value?.menuContent.toString())
        binding.apply {
            menuDetailsDate.setText(menuDetailsViewModel.menu.value?.menuDate)
            menuDetailsTarget.setText(menuDetailsViewModel.menu.value?.menuTarget)
            var menuDetailsItemList = NiftyCloudApiClient().getTrainingItemBindingMenu(fixingList) ?: emptyList()
            menuDetailsList.adapter = MenuDetailsListAdapter(menuDetailsItemList)
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