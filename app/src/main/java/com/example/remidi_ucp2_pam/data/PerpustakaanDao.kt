package com.example.remidi_ucp2_pam.data


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PerpustakaanDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertKategori(kategori: Kategori)

    @Query("SELECT * FROM kategori ORDER BY nama ASC")
    fun getAllKategori(): Flow<List<Kategori>>

    @Delete
    suspend fun deleteKategori(kategori: Kategori)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBuku(buku: Buku)

    @Update
    suspend fun updateBuku(buku: Buku)

    @Query("SELECT * FROM buku WHERE id = :id")
    fun getBukuById(id: Int): Flow<Buku>

    @Query("SELECT b.*, k.nama AS namaKategori FROM buku b LEFT JOIN kategori k ON b.idKategori = k.id ORDER BY b.judul ASC")
    fun getAllBukuWithKategori(): Flow<List<BukuWithKategori>>


    @Query("SELECT COUNT(*) FROM buku WHERE idKategori = :kategoriId AND status = 'Dipinjam'")
    suspend fun countBukuDipinjam(kategoriId: Int): Int

    @Query("DELETE FROM buku WHERE idKategori = :kategoriId")
    suspend fun deleteBukuByKategori(kategoriId: Int)

    @Query("UPDATE buku SET idKategori = NULL WHERE idKategori = :kategoriId")
    suspend fun removeKategoriFromBuku(kategoriId: Int)

    @Transaction
    suspend fun deleteKategoriTransaction(kategori: Kategori, isDeleteBooks: Boolean) {
        if (isDeleteBooks) {
            deleteBukuByKategori(kategori.id)
        } else {
            removeKategoriFromBuku(kategori.id)
        }
        deleteKategori(kategori)
    }
}

data class BukuWithKategori(
    val id: Int,
    val judul: String,
    val penulis: String,
    val penerbit: String,
    val tahunTerbit: String,
    val status: String,
    val idKategori: Int?,
    val namaKategori: String?
)