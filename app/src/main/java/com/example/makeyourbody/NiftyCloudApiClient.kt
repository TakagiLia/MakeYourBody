package com.example.makeyourbody

import android.util.Log
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

}