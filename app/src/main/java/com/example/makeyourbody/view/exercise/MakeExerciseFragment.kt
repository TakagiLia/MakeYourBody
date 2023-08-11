package com.example.makeyourbody.view.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.makeyourbody.databinding.FragmentMakeExerciseBinding
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.makeyourbody.NiftyCloudApiClient
import com.example.makeyourbody.R

class MakeExerciseFragment : Fragment() {

    private var _binding: FragmentMakeExerciseBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            exerciseRegisterBtn.setOnClickListener {
                NiftyCloudApiClient().saveExercise(
                    exerciseNameEdit.text.toString(),
                    exerciseContentEdit.text.toString()
                )
                Navigation.findNavController(view).navigate(R.id.action_make_exercise_to_top)
            }

        }

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