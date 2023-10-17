package com.takagi.makeyourbody

import android.app.Application
import android.util.Log
import com.nifcloud.mbaas.core.NCMB
import com.takagi.makeyourbody.BuildConfig.APPLICATION_KEY
import com.takagi.makeyourbody.BuildConfig.CLIENT_KEY

class ApplicationInit : Application() {

    private val applicationKey = APPLICATION_KEY
    private val clientKey = CLIENT_KEY

    override fun onCreate() {
        super.onCreate()
        Log.d("■ApplicationInit", "onCreate動く")
        // NCMBクラスのinitializeメソッドでAndroid SDKの初期化
        NCMB.initialize(
            this.applicationContext, applicationKey, clientKey
        )
    }
}
