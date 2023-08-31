package com.example.makeyourbody.view.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.makeyourbody.R
import com.example.makeyourbody.view.signup.TrainingType
import com.example.makeyourbody.databinding.FragmentTopPageBinding
import com.nifcloud.mbaas.core.NCMBUser

class TopPageFragment : Fragment() {

    private var _binding: FragmentTopPageBinding? = null

    private val binding get() = _binding!!

    private val trainer = TrainingType.TRAINER.type
    private val trainee = TrainingType.TRAINEE.type
    private val dual = TrainingType.DUAL.type

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            val userType = NCMBUser.currentuser?.get("trainingType").toString()

            //ログインユーザの属性によって表示を変える
            binding.apply {
                when(userType){
                    trainer ->{
                        topTrainerLogo.visibility = View.VISIBLE
                        scheduleBtn.visibility = View.VISIBLE
                        menuBtn.visibility = View.VISIBLE
                        makeExerciseBtn.visibility = View.VISIBLE
                    }
                    trainee ->{
                        topTraineeLogo.visibility = View.VISIBLE
                        scheduleBtn.visibility = View.VISIBLE
                    }
                    dual ->{
                        topDualLogo.visibility = View.VISIBLE
                        scheduleBtn.visibility = View.VISIBLE
                        menuBtn.visibility = View.VISIBLE
                        makeExerciseBtn.visibility = View.VISIBLE
                    }
                }

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
            }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTopPageBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}