package com.example.makeyourbody.view.exercisedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.makeyourbody.data.TrainingItem

class ExerciseDetailsViewModel : ViewModel()  {

    private val _trainingItem = MutableLiveData<TrainingItem>()
    val trainingItem get() = _trainingItem

    fun setItem(menuList: TrainingItem) {
        _trainingItem.value = menuList
    }
}