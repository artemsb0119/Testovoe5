package com.example.testovoe5

import android.app.Application
import com.onesignal.OneSignal

const val ONESIGNAL_APP_ID = "f88c4c3b-50e0-4aa2-b21a-4ecfe4a0f21b"

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()

        // Logging set to help debug issues, remove before releasing your app.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }
}