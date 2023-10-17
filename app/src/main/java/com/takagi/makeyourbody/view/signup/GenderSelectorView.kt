package com.takagi.makeyourbody.view.signup

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.takagi.makeyourbody.databinding.ViewGenderSelectorBinding

class GenderSelectorView(context: Context, attributeSet: AttributeSet?) :
    FrameLayout(context, attributeSet) {

    private var selectedGender: Gender = Gender.FEMALE
    lateinit var currentGender: Gender
        private set

    private val binding: ViewGenderSelectorBinding
    init {
        // bindingの設定
        binding = ViewGenderSelectorBinding.inflate(LayoutInflater.from(context), this, true)

        binding.apply {
                // 初期表示でセレクトされた状態にする
                genderFemale.isSelected = true

                genderFemale.setOnClickListener { selectedGender(Gender.FEMALE) }

                genderMale.setOnClickListener { selectedGender(Gender.MALE) }

                genderOther.setOnClickListener { selectedGender(Gender.OTHER) }
            }

        // 初期表示時の性別
        selectedGender(Gender.FEMALE)
    }
    private fun selectedGender(gender: Gender) {
        currentGender = gender

        binding.genderFemale.isSelected = (gender == Gender.FEMALE)
        binding.genderMale.isSelected = (gender == Gender.MALE)
        binding.genderOther.isSelected = (gender == Gender.OTHER)
    }
}
