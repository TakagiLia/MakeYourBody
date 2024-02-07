package com.takagi.makeyourbody.view.login

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.takagi.makeyourbody.R
import com.takagi.makeyourbody.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.takagi.makeyourbody.view.disabledBackPressed

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val auth: FirebaseAuth = Firebase.auth

        binding.apply {

            //ログイン画面からトップページに遷移"
            loginButton.setOnClickListener { view ->
                val loginName = loginName.text.toString()
                val loginPass = loginPass.text.toString()

                //ログイン処理
                auth.signInWithEmailAndPassword(loginName,loginPass)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("firebaseLogin", "signInWithEmail:success")
                            //ログイン成功なのでトップページに遷移
                            Navigation.findNavController(view).navigate(R.id.action_top_screen)
                        } else {
                            Log.w("firebaseLogin", "signInWithEmail:failure", task.exception)
                            //エラーメッセージ画面に出力(スナックバー)
                            Snackbar.make(
                                view,
                                getString(R.string.login_error_msg),
                                Snackbar.LENGTH_INDEFINITE
                            ).apply {
                                setTextColor(Color.BLACK)
                                setBackgroundTint(Color.parseColor("#EEE8AA"))
                                setAction("OK") { dismiss() }
                                setActionTextColor(Color.BLACK)
                                show()
                            }
                        }
                    }
            }
            //ユーザ登録画面に遷移
            signupButton.setOnClickListener { view ->
                Navigation.findNavController(view).navigate(R.id.action_signup_screen)
            }
        }
        // 戻るボタン無効化
        disabledBackPressed()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}