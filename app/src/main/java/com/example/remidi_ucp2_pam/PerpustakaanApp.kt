package com.example.remidi_ucp2_pam

import android.app.Application
import com.example.remidi_ucp2_pam.repositori.AppContainer
import com.example.remidi_ucp2_pam.repositori.AppDataContainer

class PerpustakaanApp : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}