package com.takagi.makeyourbody.view.signup

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.takagi.makeyourbody.R
import com.takagi.makeyourbody.databinding.ViewNumberPickerBinding
import com.takagi.makeyourbody.view.dialog.DialogEnum

class AgePickerDialog : DialogFragment() {

    private var _binding: ViewNumberPickerBinding? = null
    private val binding get() = _binding!!

    private val signUpViewModel : SignUpViewModel by activityViewModels()

    private val age = DialogEnum.AGE
    private val height = DialogEnum.HEIGHT
    private val weight = DialogEnum.WEIGHT

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

        val dialogType = signUpViewModel.dialogType.value
        binding.apply {

            //ダイアログの範囲設定
            hundredsPlacePicker.isVisible = false
            tensPlacePicker.maxValue = 9
            onesPlacePicker.maxValue = 9
            tensPlacePicker.minValue = 0
            onesPlacePicker.minValue = 0

            //身長体重の場合は3桁目を表示
            if(dialogType == weight || dialogType == height){
                    hundredsPlacePicker.isVisible = true
                    hundredsPlacePicker.maxValue = 9
                    hundredsPlacePicker.minValue = 0
            }

            //ダイアログ初期値受け取り、加工
            var receiveValue = when (dialogType) {
                age -> signUpViewModel.age.value ?: 0
                height -> signUpViewModel.height.value ?: 0
                weight -> signUpViewModel.weight.value ?: 0
                else -> 0
            }

            //数字の桁をバラす
            val hundredsPlace = if (receiveValue != 0 && dialogType != age) receiveValue.toString().substring(0, 1).toInt() else 0
            val onesPlace = when(dialogType){
                age -> if (receiveValue != 0)receiveValue.toString().substring(0, 1).toInt() else 0
                else -> if (receiveValue != 0)receiveValue.toString().substring(2, 3).toInt() else 0
            }
            val  tensPlace = if (receiveValue != 0)receiveValue.toString().substring(1, 2).toInt() else 0

            //ダイアログ初期値設定
            hundredsPlacePicker.value = hundredsPlace
            tensPlacePicker.value = tensPlace
            onesPlacePicker.value = onesPlace

            //ダイアログ決定ボタン押下
            pickerOkBtn.setOnClickListener {
                when (dialogType) {
                    age -> {
                        val setValue =(tensPlacePicker.value * 10) + onesPlacePicker.value
                        signUpViewModel.setAge(setValue)
                    }
                    height -> {
                        val setValue =(hundredsPlacePicker.value * 100) + (tensPlacePicker.value * 10) + onesPlacePicker.value
                        signUpViewModel.setHeight(setValue)
                    }
                    weight -> {
                        val setValue =(hundredsPlacePicker.value * 100) + (tensPlacePicker.value * 10) + onesPlacePicker.value
                        signUpViewModel.setWeight(setValue)
                    }
                    else -> {}
                }
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