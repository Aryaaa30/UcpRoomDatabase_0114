package com.example.session10_roomlocaldbpart1.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.session11_ucp2.data.entity.Dosen
import com.example.session11_ucp2.data.dao.DosenDao
import com.example.session11_ucp2.data.dao.MataKuliahDao
import com.example.session11_ucp2.data.entity.MataKuliah

// Definisi Database Room
@Database(
    entities = [Dosen::class, MataKuliah::class], // Entitas dalam database
    version = 1, // Versi database
    exportSchema = false // Jangan ekspor skema
)
abstract class AppDatabase : RoomDatabase() {

    // DAO untuk mengakses tabel Dosen
    abstract fun dosenDao(): DosenDao

    // DAO untuk mengakses tabel MataKuliah
    abstract fun mataKuliahDao(): MataKuliahDao

    companion object {
        @Volatile // Memastikan variabel instance terlihat di semua thread
        private var INSTANCE: AppDatabase? = null

        // Fungsi untuk mendapatkan instance database
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "AppDatabase" // Nama database
                )
                    .fallbackToDestructiveMigration() // Hancurkan data saat migrasi versi
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
