package com.takagi.makeyourbody.view.signup

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.takagi.makeyourbody.NiftyCloudApiClient
import com.takagi.makeyourbody.R
import com.takagi.makeyourbody.databinding.FragmentSignupBinding
import com.takagi.makeyourbody.view.dialog.DialogEnum

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding

    private val signUpViewModel: SignUpViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            //パスワード入力欄の初期InputType設定（Layoutで設定して切り替えできないためこちらに記載）
            signupPassEdit.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

            //年齢入力欄クリック
            signupAgeEdit.setOnClickListener {
                signUpViewModel.setDialogType(DialogEnum.AGE)
                AgePickerDialog().show(parentFragmentManager, tag)
            }

            //身長入力欄クリック
            signupHeightEdit.setOnClickListener {
                signUpViewModel.setDialogType(DialogEnum.HEIGHT)
                AgePickerDialog().show(parentFragmentManager, tag)
            }

            //身長入力欄クリック
            signupWeightEdit.setOnClickListener {
                signUpViewModel.setDialogType(DialogEnum.WEIGHT)
                AgePickerDialog().show(parentFragmentManager, tag)
            }


            //名前項目編集に合わせてviewModel更新
            signupNameEdit.addTextChangedListener { editText ->
                val name = editText.toString()
                signUpViewModel.setName(name)

                //文字数チェック
                if (signUpViewModel.charLength(20, name)) {
                    //空欄の場合はエラー文　
                    signupNameLengthError.visibility = View.VISIBLE
                    signupUserBtn.visibility = View.INVISIBLE
                } else {
                    //そうでない場合はエラー文非表示
                    signupNameLengthError.visibility = View.INVISIBLE
                    signupUserBtn.visibility = View.VISIBLE
                }

                //空欄チェック
                if (name.isEmpty()) {
                    //空欄の場合はエラー文　
                    signupNameError.visibility = View.VISIBLE
                } else {
                    //そうでない場合はエラー文非表示
                    signupNameError.visibility = View.INVISIBLE
                }

                //ボタン活性チェック
                signUpViewModel.checkBtnActive(signupUserBtn)
            }

            //ユーザ名の項目に合わせてViewModel更新
            signupLoginNameEdit.addTextChangedListener { editText ->
                val loginName = editText.toString()
                signUpViewModel.setLoginName(loginName)

                //文字数チェック
                if (signUpViewModel.charLength(20, loginName)) {
                    //空欄の場合はエラー文　
                    signupLoginNameLengthError.visibility = View.VISIBLE
                    signupUserBtn.visibility = View.INVISIBLE
                } else {
                    //そうでない場合はエラー文非表示
                    signupLoginNameLengthError.visibility = View.INVISIBLE
                    signupUserBtn.visibility = View.VISIBLE
                }

                //空欄チェック
                if (loginName.isEmpty()) {
                    //空欄の場合はエラー文　
                    signupLoginNameError.visibility = View.VISIBLE
                } else {
                    //そうでない場合はエラー文非表示
                    signupLoginNameError.visibility = View.INVISIBLE
                }
                //ボタン活性チェック
                signUpViewModel.checkBtnActive(signupUserBtn)
            }

            //パスワード項目編集に合わせてViewModel更新
            signupPassEdit.addTextChangedListener { editText ->
                val passWord = editText.toString()
                signUpViewModel.setPassword(signupPassEdit.text.toString())

                //文字数チェック
                if (signUpViewModel.charLength(8, passWord)) {
                    //空欄の場合はエラー文　
                    signupPassLengthError.visibility = View.VISIBLE
                } else {
                    //そうでない場合はエラー文非表示
                    signupPassLengthError.visibility = View.INVISIBLE
                }

                if (signupPassEdit.text.toString().isEmpty()) {
                    //空欄の場合はエラー文表示
                    signupPassError.visibility = View.VISIBLE
                } else {
                    //そうでない場合はエラー文非表示
                    signupPassError.visibility = View.INVISIBLE
                }
                //ボタン活性チェック
                signUpViewModel.checkBtnActive(signupUserBtn)
            }

            //ViewModel
            signUpViewModel.apply {
                //年齢項目監視
                age.observe(viewLifecycleOwner) { age ->
                    signupAgeEdit.text = age.toString()
                    Log.d("--signupAgeEdit Written--", age.toString())
                    signUpViewModel.checkBtnActive(signupUserBtn)
                }

                //身長項目監視
                height.observe(viewLifecycleOwner) { height ->
                    signupHeightEdit.text = height.toString()
                    Log.d("--signUpViewModel Written--", signUpViewModel.height.value.toString())
                    signUpViewModel.checkBtnActive(signupUserBtn)
                }

                //体重項目監視
                weight.observe(viewLifecycleOwner) { weight ->
                    signupWeightEdit.text = weight.toString()
                    Log.d("--signupWeightEdit Written--", signUpViewModel.weight.value.toString())
                    signUpViewModel.checkBtnActive(signupUserBtn)
                }
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

            //ユーザ登録ボタン押下
            signupUserBtn.setOnClickListener {
                signUpViewModel.apply {
                    //ユーザの登録処理
                    runCatching {
                        NiftyCloudApiClient().saveUserInformation(
                            name.value.toString(),
                            loginName.value.toString(),
                            password.value.toString(),
                            age.value?.toInt() ?: 0,
                            signupGenderEdit.currentGender.type,
                            height.value?.toInt() ?: 0,
                            weight.value?.toInt() ?: 0,
                            signupAttrEdit.currentTrainingType.type
                        )
                    }.onSuccess {
                        Navigation.findNavController(view).navigate(R.id.action_login_screen)
                    }.onFailure {
                        Log.d("■failure", "ユーザ登録に失敗しました" + it.message)
                    }
                }
            }

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        binding.vm = signUpViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        _binding = null
    }
}