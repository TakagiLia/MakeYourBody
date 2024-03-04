package com.takagi.makeyourbody.view.exercise

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.takagi.makeyourbody.FirebaseApiClient
import com.takagi.makeyourbody.R
import com.takagi.makeyourbody.data.TrainingItem
import com.takagi.makeyourbody.databinding.FragmentMakeExerciseBinding

class MakeExerciseFragment : Fragment() {

    private var _binding: FragmentMakeExerciseBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            exerciseNameEdit.addTextChangedListener { nameEditText ->
                if (nameEditText?.isEmpty() == true || exerciseContentEdit.text.isEmpty()) {
                    exerciseRegisterBtn.visibility = View.INVISIBLE
                    exerciseRegisterBtn.isEnabled = false
                } else {
                    exerciseRegisterBtn.visibility = View.VISIBLE
                    exerciseRegisterBtn.isEnabled = true
                }
            }

            exerciseContentEdit.addTextChangedListener { contentEditText ->
                if (exerciseNameEdit.text.isEmpty() || contentEditText?.isEmpty() == true) {
                    exerciseRegisterBtn.visibility = View.INVISIBLE
                    exerciseRegisterBtn.isEnabled = false
                } else {
                    exerciseRegisterBtn.visibility = View.VISIBLE
                    exerciseRegisterBtn.isEnabled = true
                }
            }

            //トレーニングアイテム登録処理
            exerciseRegisterBtn.setOnClickListener {
                FirebaseApiClient().saveExercise(
                    TrainingItem(
                        exerciseNameEdit.text.toString(),
                        exerciseContentEdit.text.toString(),""
                    )
                ).addOnSuccessListener {
                    Navigation.findNavController(view).navigate(R.id.action_make_exercise_to_top)

                }.addOnFailureListener { exception ->
                    Log.w("failure", "Error getting documents: ", exception)
                }
            }

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMakeExerciseBinding.inflate(inflater, container, false)

        return binding.root
    }
}