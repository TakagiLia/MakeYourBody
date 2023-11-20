package com.takagi.makeyourbody.view.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.takagi.makeyourbody.databinding.FragmentMakeExerciseBinding
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.takagi.makeyourbody.R
import com.takagi.makeyourbody.NiftyCloudApiClient

class MakeExerciseFragment : Fragment() {

    private var _binding: FragmentMakeExerciseBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            exerciseNameEdit.addTextChangedListener { nameEditText ->
                if (nameEditText?.isEmpty() == true|| exerciseContentEdit.text.isEmpty()){
                    exerciseRegisterBtn.visibility = View.INVISIBLE
                    exerciseRegisterBtn.isEnabled = false
                } else {
                    exerciseRegisterBtn.visibility = View.VISIBLE
                    exerciseRegisterBtn.isEnabled = true
                }
            }

            exerciseContentEdit.addTextChangedListener { contentEditText ->
                if(exerciseNameEdit.text.isEmpty() || contentEditText?.isEmpty() == true){
                    exerciseRegisterBtn.visibility = View.INVISIBLE
                    exerciseRegisterBtn.isEnabled = false
                }else{
                    exerciseRegisterBtn.visibility = View.VISIBLE
                    exerciseRegisterBtn.isEnabled = true
                }
            }


            exerciseRegisterBtn.setOnClickListener {
                NiftyCloudApiClient().saveExercise(
                    exerciseNameEdit.text.toString(),
                    exerciseContentEdit.text.toString(),""
                )
                Navigation.findNavController(view).navigate(R.id.action_make_exercise_to_top)
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