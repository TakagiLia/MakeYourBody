package com.example.makeyourbody.maketrainingmenu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.makeyourbody.data.TrainingItem

class MakeTrainingViewModel : ViewModel()  {

    //トレーニング作成画面　種目マスタの値
    private val _selectedItems = MutableLiveData<Set<TrainingItem>>()
    val selectedItems get() = _selectedItems

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

    fun setSelectedItem(item: TrainingItem) {
        val current = _selectedItems.value ?: emptyList()

        val new = current
            .toMutableSet()
            .apply {
                add(item)
            }
            .toSet()

        _selectedItems.value = new
    }
    fun setSelectedItems(item:List<TrainingItem>){
        _selectedItems.value = item.toSet()
    }

    //種目マスタの値を削除
    fun deleteSelectedItems(item: TrainingItem){
        val current = _selectedItems.value ?: emptyList()
        _selectedItems.value = current
            .toMutableSet()
            .apply {
                remove(item)
            }
            .toSet()
    }

}