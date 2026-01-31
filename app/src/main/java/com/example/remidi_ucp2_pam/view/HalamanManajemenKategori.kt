package com.example.remidi_ucp2_pam.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.remidi_ucp2_pam.view.viewmodel.ManajemenKategoriViewModel
import com.example.remidi_ucp2_pam.view.viewmodel.PenyediaViewModel
import com.example.remidi_ucp2_pam.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanManajemenKategori(
    onBack: () -> Unit,
    onNavigateToInsertKategori: () -> Unit,
    viewModel: ManajemenKategoriViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val listKategori by viewModel.listKategori.collectAsState()
    val errorMessage by viewModel.deleteError.collectAsState()
    val kategoriForDialog by viewModel.showOptionsDialog.collectAsState()

    if (errorMessage != null) {
        AlertDialog(
            onDismissRequest = { viewModel.dismissError() },
            title = { Text(stringResource(R.string.error_judul)) },
            text = { Text(errorMessage!!) },
            confirmButton = { TextButton(onClick = { viewModel.dismissError() }) { Text("OK") } },
            containerColor = MaterialTheme.colorScheme.errorContainer,
            titleContentColor = MaterialTheme.colorScheme.onErrorContainer
        )
    }

    if (kategoriForDialog != null) {
        AlertDialog(
            onDismissRequest = { viewModel.dismissDialog() },
            title = { Text(stringResource(R.string.dialog_hapus_title)) },
            text = { Text(stringResource(R.string.dialog_hapus_msg)) },
            confirmButton = {
                Button(
                    onClick = { viewModel.confirmDelete(isDeleteBooks = true) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) { Text(stringResource(R.string.btn_hapus_semua)) }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { viewModel.confirmDelete(isDeleteBooks = false) }
                ) { Text(stringResource(R.string.btn_hapus_kategori_saja)) }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.title_manajemen_kategori)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToInsertKategori) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { innerPadding ->
        if (listKategori.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding), contentAlignment = androidx.compose.ui.Alignment.Center) {
                Text(text = stringResource(R.string.info_kosong))
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(listKategori) { kategori ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.padding(16.dp).fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(text = kategori.nama, style = MaterialTheme.typography.titleMedium)
                                Text(text = kategori.deskripsi, style = MaterialTheme.typography.bodyMedium)
                            }
                            IconButton(onClick = {
                                viewModel.onDeleteClicked(kategori)
                            }) {
                                Icon(Icons.Default.Delete, contentDescription = null, tint = Color.Red)
                            }
                        }
                    }
                }
            }
        }
    }
}