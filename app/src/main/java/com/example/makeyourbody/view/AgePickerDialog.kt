package com.example.makeyourbody.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.example.makeyourbody.R
import com.example.makeyourbody.databinding.ViewAgePickerBinding

class AgePickerDialog : DialogFragment() {

    private var _binding: ViewAgePickerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.view_age_picker)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            ageDrum1.maxValue = 9
            ageDrum1.minValue = 0
            ageDrum2.maxValue = 9
            ageDrum2.minValue = 0

            agePickerOkBtn.setOnClickListener {
                val age = ageDrum1.value.toString() + ageDrum2.value.toString()
                val bundle = bundleOf("age_picker_value" to age)
                setFragmentResult("request_key", bundle)
                dismiss()
            }

            agePickerCancelBtn.setOnClickListener {
                dismiss()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ViewAgePickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}