package com.takagi.makeyourbody.view.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.takagi.makeyourbody.LogoutDialog
import com.takagi.makeyourbody.R
import com.takagi.makeyourbody.databinding.FragmentTopPageBinding
import com.takagi.makeyourbody.view.disabledBackPressed
import com.takagi.makeyourbody.view.login.LoginViewModel
import com.takagi.makeyourbody.view.signup.TrainingType

class TopPageFragment : Fragment() {

    private var _binding: FragmentTopPageBinding? = null

    private val loginViewModel: LoginViewModel by activityViewModels()

    private val binding get() = _binding!!

    private val trainer = TrainingType.TRAINER.type
    private val trainee = TrainingType.TRAINEE.type
    private val dual = TrainingType.DUAL.type

    private val auth: FirebaseUser? = Firebase.auth.currentUser

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            //スケジュール確認ページに遷移
            scheduleBtn.setOnClickListener {
                findNavController().navigate(R.id.action_schedule_screen)
            }

            //トレーニングメニュー作成ページに遷移
            menuBtn.setOnClickListener {
                findNavController().navigate(R.id.action_make_menu_screen)
            }

            //種目マスタ作成ページに遷移
            makeExerciseBtn.setOnClickListener {
                findNavController().navigate(R.id.action_make_exercise_screen)
            }

            //ログアウトボタン
            topLogoutBtn.setOnClickListener {
                //ログアウト確認ダイアログを出す
                LogoutDialog().show(childFragmentManager, "logout_dialog")
            }
        }

        loginViewModel.fetchUserData(auth?.uid.toString())

        //スプラッシュ（ローディング）を出して画面をさわれないようにする（大体ダイアログ出してやる）
        loginViewModel.userData.observe(viewLifecycleOwner) { userData ->

            binding.apply {
                when (userData?.type) {
                    trainer -> {
                        binding.topTrainerLogo.visibility = View.VISIBLE
                        binding.scheduleBtn.visibility = View.VISIBLE
                        binding.menuBtn.visibility = View.VISIBLE
                        binding.makeExerciseBtn.visibility = View.VISIBLE
                    }

                    trainee -> {
                        binding.topTraineeLogo.visibility = View.VISIBLE
                        binding.scheduleBtn.visibility = View.VISIBLE
                    }

                    dual -> {
                        binding.topDualLogo.visibility = View.VISIBLE
                        binding.scheduleBtn.visibility = View.VISIBLE
                        binding.menuBtn.visibility = View.VISIBLE
                        binding.makeExerciseBtn.visibility = View.VISIBLE
                    }
                }
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
        _binding = FragmentTopPageBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}