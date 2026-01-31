package com.example.remidi_ucp2_pam.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "buku",
    foreignKeys = [ForeignKey(
        entity = Kategori::class,
        parentColumns = ["id"],
        childColumns = ["idKategori"]
    )]
)
data class Buku(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val judul: String,
    val penulis: String,
    val penerbit: String,
    val tahunTerbit: String,
    val status: String,
    val idKategori: Int? = null
)