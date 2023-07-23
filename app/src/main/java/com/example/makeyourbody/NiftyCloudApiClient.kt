package com.example.makeyourbody

import android.util.Log
import com.example.makeyourbody.data.TrainingItem
import com.example.makeyourbody.data.TrainingMenu
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

    //メニューオブジェクト取得
    fun getTrainingMenu(): List<TrainingMenu> {

        var trainingMenus: List<TrainingMenu> = emptyList()

        runCatching {
            val query = NCMBQuery.forObject("main_menus")
            query.find()
        }.onSuccess { objList ->
            trainingMenus = objList.map { obj ->
                val menuDate = obj.getString("menu_date") ?: ""
                val menuTarget = obj.getString("menu_targetuser") ?: ""
                val menuTrainer = obj.getString("menu_trainer") ?: ""
                val menuContent = obj.getString("menu_content") ?: ""
                val objectId = obj.getObjectId() ?: ""
                TrainingMenu(menuDate, menuTarget, menuTrainer, menuContent, objectId)
            }
        }
        return trainingMenus
    }

    //メニューオブジェクト取得(日付絞り込み)
    fun searchTrainingMenu(searchKey :String): List<TrainingMenu> {

        var trainingMenus: List<TrainingMenu> = emptyList()

        runCatching {
            val query = NCMBQuery.forObject("main_menus")
            query.whereEqualTo("menu_date", searchKey)
            query.find()
        }.onSuccess { objList ->
            trainingMenus = objList.map { obj ->
                val menuDate = obj.getString("menu_date") ?: ""
                val menuTarget = obj.getString("menu_targetuser") ?: ""
                val menuTrainer = obj.getString("menu_trainer") ?: ""
                val menuContent = obj.getString("menu_content") ?: ""
                val objectId = obj.getObjectId() ?: ""
                TrainingMenu(menuDate, menuTarget, menuTrainer, menuContent, objectId)
            }
        }
        return trainingMenus
    }

    //メニュー詳細画面で種目を取得時に利用(リストに含まれている種目を全て取得)
    fun getTrainingItemBindingMenu(queryword: List<String>): List<TrainingItem> {
        var trainingItems: List<TrainingItem> = emptyList()

        runCatching {
            val query = NCMBQuery.forObject("training_items")
            query.whereContainedInArray("item_name", queryword)
            query.find()
        }.onSuccess { objList ->
            trainingItems = objList.map { obj ->
                val itemName = obj.getString("item_name") ?: ""
                val itemContent = obj.getString("item_content") ?: ""

                TrainingItem(itemName, itemContent)
            }
        }
        return trainingItems
    }

    //メニューレコード種目項目の文字列をバラしてリストに入れ直す
    fun trainingItemFixList(menuContent: String): List<String> {
        var fixingStr = menuContent.dropLast(1)
        fixingStr = fixingStr.drop(1).replace("\\s+".toRegex(), "")
        var fixingList = fixingStr.split(",")
        return fixingList
    }
}