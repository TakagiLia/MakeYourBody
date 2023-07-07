package com.example.makeyourbody.view.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel()  {

    private val _name = MutableLiveData<String>()
    val name get() = _name

    private val _password = MutableLiveData<String>()
    val password get() = _password

    private val _age = MutableLiveData<Int>()
    val age get() = _age

    private val _height = MutableLiveData<Int>()
    val height get() = _height

    private val _weight = MutableLiveData<Int>()
    val weight get() = _weight

    private val _dialogType = MutableLiveData<String>()
    val dialogType get() = _dialogType

    fun setAge(age : Int){
        _age.value = age
    }

    fun setHeight(height : Int){
        _height.value = height
    }

    fun setWeight(weight : Int){
        _weight.value = weight
    }

    fun setDialogType(dialogType: String) {
        _dialogType.value = dialogType
    }
}