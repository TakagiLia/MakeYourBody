package com.example.makeyourbody.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.makeyourbody.databinding.ViewGenderSelectorBinding

class GenderSelectorView(context: Context, attributeSet: AttributeSet?) :
    FrameLayout(context, attributeSet) {

    private var selectedGender: Gender = Gender.FEMALE

    private val binding: ViewGenderSelectorBinding =
        ViewGenderSelectorBinding.inflate(LayoutInflater.from(context), this, true)
            .apply {
                // 初期表示でセレクトされた状態にする
                genderFemale.isSelected = true

                genderFemale.setOnClickListener { switchGender(Gender.FEMALE) }

                genderMale.setOnClickListener { switchGender(Gender.MALE) }

                genderOther.setOnClickListener { switchGender(Gender.OTHER) }
            }

    private fun switchGender(gender: Gender) {
        selectedGender = gender

        binding.genderFemale.isSelected = (gender == Gender.FEMALE)
        binding.genderMale.isSelected = (gender == Gender.MALE)
        binding.genderOther.isSelected = (gender == Gender.OTHER)
    }

    fun getSelectedGender(): Gender = selectedGender
}

enum class Gender {
    MALE, FEMALE, OTHER
}