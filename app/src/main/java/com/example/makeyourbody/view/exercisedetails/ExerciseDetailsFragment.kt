package com.example.makeyourbody.view.exercisedetails

import android.os.Bundle
import android.util.Log
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

        binding.apply {

            exerciseDetailsName.setText(exerciseDetailsViewModel.trainingItem.value?.name)
           exerciseDetailsContent.setText(exerciseDetailsViewModel.trainingItem.value?.detail)

            exerciseDetailsNameEditBtn.setOnClickListener {
                Log.d("---ExerciseDetails---", "種目名　編集可能ボタン")
                //true or false の逆を入れるような書き方で編集の有無を制御
                exerciseDetailsName.isEnabled = !exerciseDetailsName.isEnabled
                exerciseDetailsNameEditBtn.visibility = View.INVISIBLE
                exerciseDetailsNameDisEditableBtn.visibility = View.VISIBLE
            }

            exerciseDetailsNameDisEditableBtn.setOnClickListener{
                Log.d("---ExerciseDetails---", "種目名　編集不可ボタン")
                exerciseDetailsName.isEnabled = !exerciseDetailsName.isEnabled
                exerciseDetailsNameDisEditableBtn.visibility = View.INVISIBLE
                exerciseDetailsNameEditBtn.visibility = View.VISIBLE
            }

            exerciseDetailsContentEditBtn.setOnClickListener{
                Log.d("---ExerciseDetails---", "種目内容　編集可能ボタン")
                exerciseDetailsContent.isEnabled = !exerciseDetailsContent.isEnabled
                exerciseDetailsContentEditBtn.visibility = View.INVISIBLE
                exerciseDetailsContentDisEditableBtn.visibility = View.VISIBLE
            }

            exerciseDetailsContentDisEditableBtn.setOnClickListener{
                Log.d("---ExerciseDetails---", "種目内容　編集不可ボタン")
                exerciseDetailsContent.isEnabled = !exerciseDetailsContent.isEnabled
                exerciseDetailsContentEditBtn.visibility = View.VISIBLE
                exerciseDetailsContentDisEditableBtn.visibility = View.INVISIBLE
            }

        }
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