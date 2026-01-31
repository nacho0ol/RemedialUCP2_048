package com.example.remidi_ucp2_pam.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Kategori::class, Buku::class], version = 1, exportSchema = false)
abstract class PerpustakaanDatabase : RoomDatabase() {
    abstract fun perpustakaanDao(): PerpustakaanDao

    companion object {
        @Volatile
        private var Instance: PerpustakaanDatabase? = null

        fun getDatabase(context: Context): PerpustakaanDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, PerpustakaanDatabase::class.java, "perpustakaan_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}