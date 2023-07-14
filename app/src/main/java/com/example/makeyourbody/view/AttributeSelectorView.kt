package com.example.makeyourbody.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.makeyourbody.databinding.ViewAttributeSelectorBinding

class AttributeSelectorView (context: Context, attributeSet: AttributeSet?) :
    FrameLayout(context, attributeSet) {

    private var selectedAttr: Attribute = Attribute.TRAINEE

    private val binding: ViewAttributeSelectorBinding =
        ViewAttributeSelectorBinding.inflate(LayoutInflater.from(context), this, true)
            .apply {
                // 初期表示でセレクトされた状態にする
                attrTrainee.isSelected = true

                attrTrainee.setOnClickListener { switchGender(Attribute.TRAINEE) }

                attrTrainer.setOnClickListener { switchGender(Attribute.TRAINER) }

                attrDual.setOnClickListener { switchGender(Attribute.DUAL) }
            }

    private fun switchGender(attr: Attribute) {
        selectedAttr = attr

        binding.attrTrainee.isSelected = (attr == Attribute.TRAINEE)
        binding.attrTrainer.isSelected = (attr == Attribute.TRAINER)
        binding.attrDual.isSelected = (attr == Attribute.DUAL)
    }

    fun getSelectedAttr(): Attribute = selectedAttr
}

enum class Attribute {
    TRAINEE, TRAINER, DUAL
}
