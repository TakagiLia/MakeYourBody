package com.takagi.makeyourbody.view.signup

import android.util.Log
import android.view.View
import androidx.core.view.isInvisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.takagi.makeyourbody.view.dialog.DialogEnum

class SignUpViewModel : ViewModel()  {

    private val _name = MutableLiveData<String>()
    val name get() = _name

    private val _loginName = MutableLiveData<String>()
    val loginName get() = _loginName

    private val _password = MutableLiveData<String>()
    val password get() = _password

    private val _age = MutableLiveData<Int>()
    val age get() = _age

    private val _gender = MutableLiveData<String>()
    val gender get() = _gender

    private val _height = MutableLiveData<Int>()
    val height get() = _height

    private val _weight = MutableLiveData<Int>()
    val weight get() = _weight

    private val _trainingType = MutableLiveData<String>()
    val trainingType get() = _trainingType

    private val _dialogType = MutableLiveData<DialogEnum>()
    val dialogType get() = _dialogType

    private val _isEnabled: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>().also { mutableLiveData ->
            mutableLiveData.value = false
        }
    val isEnabled: LiveData<Boolean>
        get() = _isEnabled

    fun setName(name : String){
        _name.value = name
    }
    fun setPassword(password : String){
        _password.value = password
    }
    fun setLoginName(loginName : String){
        _loginName.value = loginName
    }
    fun setAge(age : Int){
        _age.value = age
    }
    fun setGender(gender : String){
        _gender.value = gender
    }

    fun setHeight(height : Int){
        _height.value = height
    }
    fun setWeight(weight : Int){
        _weight.value = weight
    }
    fun setTrainingType(trainingType : String){
        _trainingType.value = trainingType
    }
    fun setDialogType(dialogType: DialogEnum) {
        _dialogType.value = dialogType
    }

    fun changeName(isBlank: Boolean,name : String){
        _isEnabled.value = !isBlank
        if(!isBlank){
            _name.value = name
        }
    }

    fun checkBtnActive(activeBtn : View) {

        var count : Int

        count = 0
        count += if(!_name.value.isNullOrEmpty()) 1 else 0
        count += if(!_password.value.isNullOrEmpty()) 1 else 0
        count += if(!_loginName.value.isNullOrEmpty()) 1 else 0
        count += if(_age.value != null) 1 else 0
        count += if(_height.value != null) 1 else 0
        count += if(_weight.value != null) 1 else 0
        Log.d("--count--", count.toString() + (count == 6).toString())

        if(count == 6){
            activeBtn.apply {
                count = 0
                visibility = View.VISIBLE
                isEnabled = true
            }
        }else{
            activeBtn.apply {
                visibility = View.INVISIBLE
                isEnabled = false
            }
        }
    }

    fun charLength(length : Int,target : String) : Boolean{
        Log.d("--charLength--", target.count().toString())
        return  target.count() >= length
    }
}
