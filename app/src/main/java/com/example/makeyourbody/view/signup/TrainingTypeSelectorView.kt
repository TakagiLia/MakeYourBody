package com.example.makeyourbody.view.signup

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.makeyourbody.databinding.ViewAttributeSelectorBinding

class TrainingTypeSelectorView(context: Context, attributeSet: AttributeSet?) :
    FrameLayout(context, attributeSet) {

    private val binding: ViewAttributeSelectorBinding

    lateinit var currentTrainingType: TrainingType
        private set

    init {
        // bindingの設定
        binding = ViewAttributeSelectorBinding.inflate(LayoutInflater.from(context), this, true)

        // setOnClickListenerの設定
        binding.apply {
            attrTrainee.setOnClickListener { selectedTrainingType(TrainingType.TRAINEE) }
            attrTrainer.setOnClickListener { selectedTrainingType(TrainingType.TRAINER) }
            attrDual.setOnClickListener { selectedTrainingType(TrainingType.DUAL) }
        }

        // 初期表示時のTrainingTypeを設定
        selectedTrainingType(TrainingType.TRAINEE)
    }

    private fun selectedTrainingType(trainingType: TrainingType) {
        currentTrainingType = trainingType

        binding.attrTrainee.isSelected = (trainingType == TrainingType.TRAINEE)
        binding.attrTrainer.isSelected = (trainingType == TrainingType.TRAINER)
        binding.attrDual.isSelected = (trainingType == TrainingType.DUAL)
    }
}

