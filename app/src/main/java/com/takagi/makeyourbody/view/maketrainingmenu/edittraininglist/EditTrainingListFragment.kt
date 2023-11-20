package com.takagi.makeyourbody.view.maketrainingmenu.edittraininglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.takagi.makeyourbody.databinding.FragmentListSelectedTrainingBinding

class EditTrainingListFragment :Fragment(){

    private var _binding: FragmentListSelectedTrainingBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListSelectedTrainingBinding.inflate(inflater, container, false)

        return binding.root
    }
}