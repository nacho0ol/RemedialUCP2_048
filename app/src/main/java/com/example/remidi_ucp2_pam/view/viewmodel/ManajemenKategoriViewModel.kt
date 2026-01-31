package com.example.remidi_ucp2_pam.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remidi_ucp2_pam.data.Kategori
import com.example.remidi_ucp2_pam.repositori.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ManajemenKategoriViewModel(private val repository: Repository) : ViewModel() {
    val listKategori: StateFlow<List<Kategori>> = repository.getAllKategori()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _showOptionsDialog = MutableStateFlow<Kategori?>(null)
    val showOptionsDialog: StateFlow<Kategori?> = _showOptionsDialog

    private val _deleteError = MutableStateFlow<String?>(null)
    val deleteError: StateFlow<String?> = _deleteError

    fun onDeleteClicked(kategori: Kategori) {
        viewModelScope.launch {
            val jumlahDipinjam = repository.countBukuDipinjam(kategori.id)

            if (jumlahDipinjam > 0) {
                _deleteError.value = "Gagal menghapus! Ada $jumlahDipinjam buku berstatus 'Dipinjam' di kategori ini."
            } else {
                _showOptionsDialog.value = kategori
            }
        }
    }

    fun confirmDelete(isDeleteBooks: Boolean) {
        val kategori = _showOptionsDialog.value
        if (kategori != null) {
            viewModelScope.launch {
                repository.deleteKategori(kategori, isDeleteBooks)
                _showOptionsDialog.value = null
            }
        }
    }

    fun dismissError() {
        _deleteError.value = null
    }

    fun dismissDialog() {
        _showOptionsDialog.value = null
    }
}