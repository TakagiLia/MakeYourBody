package com.example.makeyourbody.view.signup

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.makeyourbody.R
import com.example.makeyourbody.data.SignUpUser
import com.example.makeyourbody.databinding.FragmentSignupBinding
import com.example.makeyourbody.view.AgePickerDialog
import com.example.makeyourbody.view.DialogEnum

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null

    private val binding get() = _binding!!
    private val signUpViewModel: SignUpViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            //パスワード入力欄の初期InputType設定（Layoutで設定して切り替えできないためこちらに記載）
            signupPassEdit.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

            //年齢入力欄クリック
            signupAgeEdit.setOnClickListener {

                signUpViewModel.setDialogType(DialogEnum.AGE.type)
                AgePickerDialog().show(parentFragmentManager, tag)

            }

            //年齢項目監視
            signUpViewModel.age.observe(viewLifecycleOwner) { age ->
                signupAgeEdit.text = age.toString()
            }

            //パスワードマスクボタン
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