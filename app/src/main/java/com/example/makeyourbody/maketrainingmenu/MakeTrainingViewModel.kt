package com.example.makeyourbody.maketrainingmenu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MakeTrainingViewModel : ViewModel()  {

    //トレーニング作成画面　セッション日付
    private val _menuDate = MutableLiveData<String>()
    val menuDate get() = _menuDate

    //トレーニング作成画面　対象ユーザ
    private val _menuTargetUser = MutableLiveData<String>()
    val menuTargetUser get() = _menuTargetUser

    fun setMenuDate(Date :String){
        _menuDate.value = Date
    }
    fun setTargetUser(targetUser :String){
        _menuTargetUser.value = targetUser
    }

}