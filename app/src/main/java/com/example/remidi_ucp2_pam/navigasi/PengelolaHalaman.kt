package com.example.remidi_ucp2_pam.navigasi

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.remidi_ucp2_pam.view.HalamanEntryBuku
import com.example.remidi_ucp2_pam.view.HalamanEntryKategori
import com.example.remidi_ucp2_pam.view.HalamanHome
import com.example.remidi_ucp2_pam.view.HalamanManajemenKategori

@Composable
fun PengelolaHalaman() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HalamanHome(
                onNavigateToInsertBuku = { navController.navigate("insert_buku") },
                onNavigateToManajemenKategori = { navController.navigate("manajemen_kategori") },
                onNavigateToUpdateBuku = { id -> navController.navigate("update_buku/$id") }
            )
        }
        composable("insert_buku") {
            HalamanEntryBuku(onBack = { navController.popBackStack() })
        }
        composable(
            "update_buku/{idBuku}",
            arguments = listOf(navArgument("idBuku") { type = NavType.IntType })
        ) {
            HalamanEntryBuku(onBack = { navController.popBackStack() })
        }
        composable("manajemen_kategori") {
            HalamanManajemenKategori(
                onBack = { navController.popBackStack() },
                onNavigateToInsertKategori = { navController.navigate("insert_kategori") }
            )
        }
        composable("insert_kategori") {
            HalamanEntryKategori(onBack = { navController.popBackStack() })
        }
    }
}