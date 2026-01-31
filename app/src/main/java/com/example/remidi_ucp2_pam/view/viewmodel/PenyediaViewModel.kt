package com.example.remidi_ucp2_pam.view.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.remidi_ucp2_pam.PerpustakaanApp

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer { HomeViewModel(aplikasi().container.repository) }
        initializer { KategoriViewModel(aplikasi().container.repository) }
        initializer { BukuViewModel(aplikasi().container.repository) }
        initializer { ManajemenKategoriViewModel(aplikasi().container.repository) }
    }
}

fun CreationExtras.aplikasi(): PerpustakaanApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PerpustakaanApp)