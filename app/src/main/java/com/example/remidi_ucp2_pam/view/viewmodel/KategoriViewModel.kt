package com.example.remidi_ucp2_pam.view.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remidi_ucp2_pam.data.Kategori
import com.example.remidi_ucp2_pam.repositori.Repository
import kotlinx.coroutines.launch

class KategoriViewModel(private val repository: Repository) : ViewModel() {
    var uiState by mutableStateOf(KategoriUIState())
        private set

    fun updateUiState(kategori: KategoriUIState) {
        uiState = kategori
    }

    fun saveKategori() {
        viewModelScope.launch {
            repository.insertKategori(uiState.toEntity())
        }
    }
}

data class KategoriUIState(
    val nama: String = "",
    val deskripsi: String = ""
)

fun KategoriUIState.toEntity(): Kategori = Kategori(
    nama = nama,
    deskripsi = deskripsi
)