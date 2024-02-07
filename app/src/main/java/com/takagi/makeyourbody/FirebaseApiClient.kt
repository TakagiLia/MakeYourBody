package com.takagi.makeyourbody

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class FirebaseApiClient {
    private val fireStore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun getUserInfo(userId: String): Task<QuerySnapshot> {
        return fireStore.collection("userInfo")
            .whereEqualTo("userid", userId)
            .get()
    }

}