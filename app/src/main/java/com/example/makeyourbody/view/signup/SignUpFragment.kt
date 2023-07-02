package com.example.makeyourbody.view.signup

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.makeyourbody.R
import com.example.makeyourbody.databinding.FragmentSignupBinding
import com.example.makeyourbody.view.AgePickerDialog

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            signupPassEdit.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

            signupAgeEdit.setOnClickListener {
                AgePickerDialog().show(parentFragmentManager, tag)

                parentFragmentManager.setFragmentResultListener(
                    "request_key",
                    viewLifecycleOwner
                ) { _, result: Bundle ->
                    signupAgeEdit.setText(result.getString("age_picker_value"))
                }
            }

            signupPassBtn.setOnClickListener {
                Log.d("---passButton---", "onclick")
                if (signupPassEdit.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    Log.d("---passButton---", "true：　" + signupPassEdit.inputType)
                    signupPassEdit.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    signupPassBtn.setImageResource(R.drawable.visible_icon24)
                } else {
                    Log.d("---passButton---", "false：　" + signupPassEdit.inputType)
                    signupPassEdit.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    signupPassBtn.setImageResource(R.drawable.hide_icon24)
                }
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