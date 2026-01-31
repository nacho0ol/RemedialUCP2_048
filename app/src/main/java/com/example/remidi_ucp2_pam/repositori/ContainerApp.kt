package com.example.remidi_ucp2_pam.repositori

import android.content.Context
import com.example.remidi_ucp2_pam.data.PerpustakaanDatabase

interface AppContainer {
    val repository: Repository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val repository: Repository by lazy {
        LocalRepository(PerpustakaanDatabase.getDatabase(context).perpustakaanDao())
    }
}