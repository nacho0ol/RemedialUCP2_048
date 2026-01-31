package com.example.remidi_ucp2_pam.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.remidi_ucp2_pam.R
import com.example.remidi_ucp2_pam.view.viewmodel.HomeViewModel
import com.example.remidi_ucp2_pam.view.viewmodel.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanHome(
    onNavigateToInsertBuku: () -> Unit,
    onNavigateToManajemenKategori: () -> Unit,
    onNavigateToUpdateBuku: (Int) -> Unit,
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val listBuku by viewModel.listBuku.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.title_home)) },
                actions = {
                    IconButton(onClick = onNavigateToManajemenKategori) {
                        Icon(Icons.Default.List, contentDescription = null)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToInsertBuku) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { innerPadding ->
        if (listBuku.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding), contentAlignment = androidx.compose.ui.Alignment.Center) {
                Text(text = stringResource(R.string.info_kosong))
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(listBuku) { buku ->
                    Card(
                        modifier = Modifier.fillMaxWidth().clickable { onNavigateToUpdateBuku(buku.id) },
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = buku.judul, style = MaterialTheme.typography.titleLarge)
                            Text(text = "${stringResource(R.string.label_penulis)}: ${buku.penulis}")
                            Text(text = "${stringResource(R.string.label_pilih_kategori)}: ${buku.namaKategori ?: stringResource(R.string.info_tanpa_kategori)}")
                            Text(
                                text = "${stringResource(R.string.label_status)}: ${buku.status}",
                                color = if (buku.status == "Tersedia") Color.Green else Color.Red
                            )
                        }
                    }
                }
            }
        }
    }
}

