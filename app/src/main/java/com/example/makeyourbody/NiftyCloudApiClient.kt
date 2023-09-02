package com.example.makeyourbody

import android.util.Log
import com.example.makeyourbody.data.TrainingItem
import com.example.makeyourbody.data.TrainingMenu
import com.nifcloud.mbaas.core.NCMBObject
import com.nifcloud.mbaas.core.NCMBQuery
import com.nifcloud.mbaas.core.NCMBUser
import java.util.Date

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
                var menuDate = obj.getString("menu_date") ?: ""
                val menuTarget = obj.getString("menu_targetuser") ?: ""
                val menuTrainer = obj.getString("menu_trainer") ?: ""
                val menuContent = obj.getString("menu_content") ?: ""
                val objectId = obj.getObjectId() ?: ""
                menuDate = menuDate.substring(24,34).replace("-","/")
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

    //メニュー登録画面でSave時
    fun saveMenuObject(selectedItems: String, menuDateEdit: Date, menuTargetEdit: String) {
        runCatching {
            var ncmbObj = NCMBObject("main_menus")

            ncmbObj.put("menu_content", selectedItems)
            ncmbObj.put("menu_date", menuDateEdit)
            ncmbObj.put("menu_targetuser", menuTargetEdit)
            ncmbObj.save()
        }.onSuccess {
            Log.d("--success--", "メニュー登録に成功しました")
        }.onFailure {
            Log.d("--failure--", "メニュー登録に失敗しました" + it.message)
        }
    }

    //UserSignUp画面でSave時
    fun saveUserInformation(name :String,loginName : String,pass : String,age :Int,gender : String,height :Int,
                            weight :Int,trainingType :String){
        runCatching {
            //　Userインスタンスの生成
            var user = NCMBUser()
            // ユーザー名・パスワードを設定
            user.userName = loginName
            user.password = pass
            user.put("name",name)
            user.put("age",age)
            user.put("gender",gender)
            user.put("height",height)
            user.put("weight",weight)
            user.put("trainingType",trainingType)
            user.signUp()
        }.onSuccess {
            Log.d("--sign up success--", "")
        }.onFailure {
            Log.d("--failure--", "ユーザ登録に失敗しました" + it.message)
        }
    }

    //種目マスタの登録
    fun saveExercise(name :String,content : String){

        runCatching {
            val obj = NCMBObject("training_items")
            obj.put("item_name",name)
            obj.put("item_content",content)
            obj.save()
        }.onSuccess {
            Log.d("--saveExercise success--", "")
        }.onFailure {
            Log.d("--failure--", "ユーザ登録に失敗しました" + it.message)
        }

    }

    fun updateTrainingMenu(updateTrainingMenu :TrainingMenu){

        runCatching {

            // TestClassへのNCMBObjectを設定
            val obj = NCMBObject("main_menus")
            // objectIdプロパティを設定
            obj.setObjectId(updateTrainingMenu.objectId)
            // オブジェクトに値を設定
            obj.put("menu_date",  CommonFormatter().dateConvert(updateTrainingMenu.menuDate))
            obj.put("menu_targetuser", updateTrainingMenu.menuTarget)
            obj.put("menu_trainer", updateTrainingMenu.menuTrainer)//updateTrainingMenu.menuTrainer != null ?
            obj.put("menu_content", updateTrainingMenu.menuContent)
            obj.save()
        }.onSuccess {
            Log.d("■updateTrainingMenu","TrainingMenu更新成功")
        }.onFailure {
            Log.d("--failure--", "メニュー編集時の登録に失敗しました（メニュー詳細画面）" + it.message)
        }
    }

    fun logOut(){
        runCatching {
            val user = NCMBUser()
            // ログアウト
            user.logout()
        }.onSuccess {
            Log.d("--success--","ログアウトに成功しました")
        }.onFailure {
            Log.d("--failure--", "ログアウトに失敗しました" + it.message)
        }
        }


}