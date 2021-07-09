package com.example.kakaologinkotlin

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "0fb90095ab2bcd323decd3974882152d")
    }
}