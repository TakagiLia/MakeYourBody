package com.example.makeyourbody.view.menudetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.makeyourbody.data.TrainingMenu

class MenuDetailsViewModel : ViewModel() {

    private val _menuList = MutableLiveData<Set<TrainingMenu>>()
    val menuList get() = _menuList

    private val _menu = MutableLiveData<TrainingMenu>()
    val menu get() = _menu

    private val _menuDate = MutableLiveData<String>()
    val menuDate get() = _menuDate

    private val _menuTarget = MutableLiveData<String>()
    val menuTarget get() = _menuTarget

    fun setMenus(menuList: List<TrainingMenu>) {
        _menuList.value = menuList.toSet()
    }

    fun setMenu(menu: TrainingMenu) {
        _menu.value = menu
    }
    fun setMenuDate(date : String){
        _menuDate.value = date
    }

    fun setMenuTarget(target : String){
        _menuDate.value = target
    }
}