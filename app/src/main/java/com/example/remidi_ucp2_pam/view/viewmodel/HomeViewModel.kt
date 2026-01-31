package com.example.remidi_ucp2_pam.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remidi_ucp2_pam.data.BukuWithKategori
import com.example.remidi_ucp2_pam.repositori.Repository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(private val repository: Repository) : ViewModel() {
    val listBuku: StateFlow<List<BukuWithKategori>> = repository.getAllBuku()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}