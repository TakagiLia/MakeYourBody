package com.example.makeyourbody

import android.app.Application
import android.os.Build
import android.util.Log
import com.nifcloud.mbaas.core.NCMB
import com.example.makeyourbody.BuildConfig
import com.example.makeyourbody.BuildConfig.APPLICATION_KEY
import com.example.makeyourbody.BuildConfig.CLIENT_KEY

class ApplicationInit : Application() {

    private val applicationKey = APPLICATION_KEY
    private val clientKey = CLIENT_KEY

    override fun onCreate() {
        super.onCreate()
//        BuildConfig.DEBUG
        Log.d("■ApplicationInit","onCreate動く")
        //ニフクラ mobile backendのアプリケーションキーとクライアントキーを利用して、
        // NCMBクラスのinitializeメソッドでAndroid SDKの初期化
        NCMB.initialize(
            this.applicationContext,applicationKey,clientKey)
    }
}