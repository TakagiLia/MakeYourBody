package com.takagi.makeyourbody.view.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.toObjects
import com.takagi.makeyourbody.FirebaseApiClient
import com.takagi.makeyourbody.data.UserInformation
import com.takagi.makeyourbody.data.raw.UserInfoRaw
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    val userData = MutableLiveData<UserInformation?>()

    fun fetchUserData(userId: String) {
        viewModelScope.launch {
            FirebaseApiClient()
                .getUserInfo(userId)
                .addOnSuccessListener { documents ->
                    userData.value = documents
                        .toObjects<UserInfoRaw>()
                        .first()
                        .toUserInformation()
                }
                .addOnFailureListener { exception ->
                    // エラー処理
                    Log.e("firebaseLogin", "signInWithEmail:failure", exception)
                }
        }
    }
}