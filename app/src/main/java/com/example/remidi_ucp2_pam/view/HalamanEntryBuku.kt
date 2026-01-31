package com.example.remidi_ucp2_pam.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.remidi_ucp2_pam.R
import com.example.remidi_ucp2_pam.view.viewmodel.BukuViewModel
import com.example.remidi_ucp2_pam.view.viewmodel.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanEntryBuku(
    onBack: () -> Unit,
    viewModel: BukuViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState
    val listKategori by viewModel.listKategori.collectAsState()

    var expandedKategori by remember { mutableStateOf(false) }
    var selectedKategoriName by remember { mutableStateOf("") }

    LaunchedEffect(uiState.idKategori, listKategori) {
        selectedKategoriName = listKategori.find { it.id == uiState.idKategori }?.nama ?: ""
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.title_tambah_buku)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.Companion.padding(innerPadding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = uiState.judul,
                onValueChange = { viewModel.updateUiState(uiState.copy(judul = it)) },
                label = { Text(stringResource(R.string.label_judul)) },
                modifier = Modifier.Companion.fillMaxWidth()
            )
            OutlinedTextField(
                value = uiState.penulis,
                onValueChange = { viewModel.updateUiState(uiState.copy(penulis = it)) },
                label = { Text(stringResource(R.string.label_penulis)) },
                modifier = Modifier.Companion.fillMaxWidth()
            )
            OutlinedTextField(
                value = uiState.penerbit,
                onValueChange = { viewModel.updateUiState(uiState.copy(penerbit = it)) },
                label = { Text(stringResource(R.string.label_penerbit)) },
                modifier = Modifier.Companion.fillMaxWidth()
            )
            OutlinedTextField(
                value = uiState.tahunTerbit,
                onValueChange = { viewModel.updateUiState(uiState.copy(tahunTerbit = it)) },
                label = { Text(stringResource(R.string.label_tahun)) },
                modifier = Modifier.Companion.fillMaxWidth()
            )
            OutlinedTextField(
                value = uiState.status,
                onValueChange = { viewModel.updateUiState(uiState.copy(status = it)) },
                label = { Text(stringResource(R.string.label_status)) },
                modifier = Modifier.Companion.fillMaxWidth()
            )

            ExposedDropdownMenuBox(
                expanded = expandedKategori,
                onExpandedChange = { expandedKategori = !expandedKategori }
            ) {
                OutlinedTextField(
                    value = selectedKategoriName,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text(stringResource(R.string.label_pilih_kategori)) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedKategori) },
                    modifier = Modifier.Companion.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expandedKategori,
                    onDismissRequest = { expandedKategori = false }
                ) {
                    listKategori.forEach { kategori ->
                        DropdownMenuItem(
                            text = { Text(kategori.nama) },
                            onClick = {
                                viewModel.updateUiState(uiState.copy(idKategori = kategori.id))
                                selectedKategoriName = kategori.nama
                                expandedKategori = false
                            }
                        )
                    }
                }
            }

            Button(
                onClick = {
                    viewModel.saveBuku()
                    onBack()
                },
                modifier = Modifier.Companion.fillMaxWidth()
            ) {
                Text(stringResource(R.string.btn_simpan))
            }
        }
    }
}