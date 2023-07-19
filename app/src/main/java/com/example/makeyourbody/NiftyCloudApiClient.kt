package com.example.makeyourbody

import android.util.Log
import com.example.makeyourbody.data.TrainingItem
import com.nifcloud.mbaas.core.NCMBQuery
import com.nifcloud.mbaas.core.NCMBUser

class NiftyCloudApiClient {

    //ログイン画面でトップページにログイン
    fun loginConfirm(nameEdit: String, passEdit: String) : Boolean {
        var result: Boolean = false
        runCatching {
            //　Userインスタンスの生成
            var user = NCMBUser()
            // ユーザー名・パスワードを設定
//            user.userName = nameEdit
//            user.password = passEdit
            user.userName = "yamamoto"
            user.password = "1234"
            user.login()
        }.onSuccess {
            // ログインに成功した場合
            result = true
        }.onFailure {
            //ログイン失敗の場合
            Log.d("---Login failure---", "ログインに失敗しました" + it.message)
            result = false
        }
        return result
    }

    //種目マスタのリスト値を全て取得
    fun getTrainingItemList(): List<TrainingItem> {
        var trainingItems: List<TrainingItem> = emptyList()

        runCatching {
            NCMBQuery.forObject("training_items").find()
        }.onSuccess { objList ->
            trainingItems = objList.map { obj ->
                val itemName = obj.getString("item_name") ?: ""
                val itemContent = obj.getString("item_content") ?: ""

                TrainingItem(itemName, itemContent)
            }
        }
        return trainingItems
    }
}