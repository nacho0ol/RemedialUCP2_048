package com.example.remidi_ucp2_pam.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.remidi_ucp2_pam.R
import com.example.remidi_ucp2_pam.view.viewmodel.KategoriViewModel
import com.example.remidi_ucp2_pam.view.viewmodel.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanEntryKategori(
    onBack: () -> Unit,
    viewModel: KategoriViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.title_tambah_kategori)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = uiState.nama,
                onValueChange = { viewModel.updateUiState(uiState.copy(nama = it)) },
                label = { Text(stringResource(R.string.label_nama_kategori)) },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = uiState.deskripsi,
                onValueChange = { viewModel.updateUiState(uiState.copy(deskripsi = it)) },
                label = { Text(stringResource(R.string.label_deskripsi)) },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    viewModel.saveKategori()
                    onBack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.btn_simpan))
            }
        }
    }
}