package com.example.makeyourbody.view

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.makeyourbody.R
import com.example.makeyourbody.databinding.ViewNumberPickerBinding
import com.example.makeyourbody.view.signup.SignUpViewModel

class AgePickerDialog : DialogFragment() {

    private var _binding: ViewNumberPickerBinding? = null
    private val binding get() = _binding!!

    private val signUpViewModel : SignUpViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())

        dialog.setContentView(R.layout.view_number_picker)
        //ダイアログのサイズを設定
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            //ダイアログの範囲設定
            tensPlacePicker.maxValue = 9
            onesPlacePicker.maxValue = 9
            tensPlacePicker.minValue = 0
            onesPlacePicker.minValue = 0

            //ダイアログ初期値受け取り、加工
            var ageInit = signUpViewModel.age.value ?: 0
            var tensPlace = if (ageInit != 0)ageInit.toString().substring(0, 1).toInt() else 0
            var onesPlace = if (ageInit != 0)ageInit.toString().substring(1, 2).toInt() else 0

            //ダイアログ初期値設定
            tensPlacePicker.value = tensPlace
            onesPlacePicker.value = onesPlace

            //ダイアログ決定ボタン押下
            pickerOkBtn.setOnClickListener {
                val age = (tensPlacePicker.value * 10) + onesPlacePicker.value
                signUpViewModel.setAge(age)
                dismiss()
            }

            //ダイアログキャンセルボタン押下
            pickerCancelBtn.setOnClickListener {
                dismiss()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ViewNumberPickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}