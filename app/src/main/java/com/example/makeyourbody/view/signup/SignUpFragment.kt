package com.example.makeyourbody.view.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.makeyourbody.databinding.FragmentSignupBinding
import com.example.makeyourbody.view.AgePickerDialog

class SignUpFragment : Fragment(){

    private var _binding:  FragmentSignupBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signupAgeEdit.setOnClickListener {
            AgePickerDialog().show(parentFragmentManager, tag)

            parentFragmentManager.setFragmentResultListener(
                "request_key",
                viewLifecycleOwner
            ) { _, result: Bundle ->
                binding.signupAgeEdit.setText(result.getString("age_picker_value"))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}