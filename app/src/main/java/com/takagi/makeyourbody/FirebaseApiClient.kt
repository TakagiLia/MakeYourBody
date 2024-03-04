package com.takagi.makeyourbody

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.takagi.makeyourbody.data.TrainingItem

class FirebaseApiClient {
    private val fireStore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun getUserInfo(userId: String): Task<QuerySnapshot> {
        return fireStore.collection("userInfo")
            .whereEqualTo("userid", userId)
            .get()
    }

    //種目マスタの登録(新規登録)
    fun saveExercise(newExerciseItem : TrainingItem): Task<Void> {
        return fireStore.collection("training_item").document().set(newExerciseItem)
    }

}