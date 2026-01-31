package com.example.remidi_ucp2_pam.repositori

import com.example.remidi_ucp2_pam.data.Buku
import com.example.remidi_ucp2_pam.data.BukuWithKategori
import com.example.remidi_ucp2_pam.data.Kategori
import com.example.remidi_ucp2_pam.data.PerpustakaanDao
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun insertKategori(kategori: Kategori)
    fun getAllKategori(): Flow<List<Kategori>>

    suspend fun deleteKategori(kategori: Kategori, deleteBooks: Boolean)
    suspend fun countBukuDipinjam(kategoriId: Int): Int

    suspend fun insertBuku(buku: Buku)
    suspend fun updateBuku(buku: Buku)
    fun getAllBuku(): Flow<List<BukuWithKategori>>
    fun getBukuById(id: Int): Flow<Buku>
}

class LocalRepository(private val dao: PerpustakaanDao) : Repository {
    override suspend fun insertKategori(kategori: Kategori) = dao.insertKategori(kategori)
    override fun getAllKategori(): Flow<List<Kategori>> = dao.getAllKategori()

    override suspend fun deleteKategori(kategori: Kategori, deleteBooks: Boolean) {
        dao.deleteKategoriTransaction(kategori, deleteBooks)
    }

    override suspend fun countBukuDipinjam(kategoriId: Int): Int {
        return dao.countBukuDipinjam(kategoriId)
    }

    override suspend fun insertBuku(buku: Buku) = dao.insertBuku(buku)
    override suspend fun updateBuku(buku: Buku) = dao.updateBuku(buku)
    override fun getAllBuku(): Flow<List<BukuWithKategori>> = dao.getAllBukuWithKategori()
    override fun getBukuById(id: Int): Flow<Buku> = dao.getBukuById(id)
}