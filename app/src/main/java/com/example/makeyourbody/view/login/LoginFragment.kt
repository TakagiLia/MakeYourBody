package com.example.makeyourbody.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.makeyourbody.R
import com.example.makeyourbody.databinding.FragmentLoginBinding
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //ログイン画面からトップページに遷移
        binding.loginBtn.setOnClickListener{view ->
            Navigation.findNavController(view).navigate(R.id.action_top_screen)
        }

        //ユーザ登録画面に遷移
        binding.signupBtn.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(R.id.action_signup_screen)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}