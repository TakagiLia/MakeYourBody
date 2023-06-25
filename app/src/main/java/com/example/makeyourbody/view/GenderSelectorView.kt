package com.example.makeyourbody.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.example.makeyourbody.R
import com.example.makeyourbody.databinding.ViewGenderSelectorBinding

class GenderSelectorView(context: Context, attributeSet: AttributeSet?) :
    FrameLayout(context, attributeSet) {

    private val binding =
        ViewGenderSelectorBinding.inflate(LayoutInflater.from(context), this, true).apply {

            //ボタン押下時に背景色変更している
            genderFemale.setOnClickListener {
                backgroundChange(genderFemale, genderMale, genderOther)
            }

            genderMale.setOnClickListener {
                backgroundChange(genderMale, genderFemale, genderOther)
            }

            genderOther.setOnClickListener {
                backgroundChange(genderOther, genderFemale, genderMale)
            }
        }

    private fun backgroundChange(pressed: View, released1: View, released2: View) {
        if (pressed.isPressed) {
            pressed.setBackgroundColor(getResources().getColor(R.color.primary_color))
            released1.setBackgroundColor(getResources().getColor(R.color.pale_red_color))
            released2.setBackgroundColor(getResources().getColor(R.color.pale_red_color))
        } else {
            pressed.setBackgroundColor(getResources().getColor(R.color.pale_red_color))
        }

    }
}