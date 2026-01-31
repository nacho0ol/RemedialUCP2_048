package com.example.remidi_ucp2_pam.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kategori")
data class Kategori(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String,
    val deskripsi: String
)