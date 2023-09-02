package com.example.makeyourbody.view.maketrainingmenu.selectedtraininglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.makeyourbody.databinding.FragmentListSelectedTrainingBinding

class EditTrainingListFragment :Fragment(){

    private var _binding: FragmentListSelectedTrainingBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListSelectedTrainingBinding.inflate(inflater, container, false)

        return binding.root
    }
}