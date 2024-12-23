package com.example.session10_roomlocaldbpart1.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.session11_ucp2.data.entity.Dosen
import com.example.session11_ucp2.data.dao.DosenDao
import com.example.session11_ucp2.data.dao.MataKuliahDao
import com.example.session11_ucp2.data.entity.MataKuliah


@Database(
    entities = [Dosen::class, MataKuliah::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){ // Mendefiniskan Fungsi untuk mengakses data mahasiswa
    abstract fun dosenDao(): DosenDao
    abstract fun mataKuliahDao(): MataKuliahDao

    companion object{
        @Volatile //Memastikan bahwa nilai variable instance selalu sama di se
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, // Class database
                    "AppDatabase" // Nama Database
                )
                    .build().also { Instance = it }
            })
        }
    }
}