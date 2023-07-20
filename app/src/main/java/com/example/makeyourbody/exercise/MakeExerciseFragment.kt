package com.example.makeyourbody.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.makeyourbody.databinding.FragmentMakeExerciseBinding
import androidx.fragment.app.Fragment

class MakeExerciseFragment :Fragment() {

    private var _binding:  FragmentMakeExerciseBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMakeExerciseBinding.inflate(inflater, container, false)

        return binding.root
    }
}