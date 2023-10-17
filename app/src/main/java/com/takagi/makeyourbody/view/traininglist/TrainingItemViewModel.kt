package com.takagi.makeyourbody.view.traininglist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.takagi.makeyourbody.data.TrainingItem

class TrainingItemViewModel : ViewModel()  {

    private val _trainingItem = MutableLiveData<TrainingItem>()
    val trainingItem get() = _trainingItem

    fun setItem(menuList: TrainingItem) {
        _trainingItem.value = menuList
    }
}