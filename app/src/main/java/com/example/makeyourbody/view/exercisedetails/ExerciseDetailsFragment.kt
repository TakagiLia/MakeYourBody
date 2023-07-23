package com.example.makeyourbody.view.exercisedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.makeyourbody.databinding.FragmentExerciseDetailsBinding


class ExerciseDetailsFragment : Fragment() {

    private var _binding: FragmentExerciseDetailsBinding? = null
    private val binding get() = _binding!!

    private val exerciseDetailsViewModel: ExerciseDetailsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.exerciseDetailsName.setText(exerciseDetailsViewModel.trainingItem.value?.name)
        binding.exerciseDetailsContent.setText(exerciseDetailsViewModel.trainingItem.value?.detail)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExerciseDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}