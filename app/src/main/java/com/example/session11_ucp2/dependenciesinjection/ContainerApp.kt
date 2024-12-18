package com.example.session11_ucp2.dependenciesinjection

import android.content.Context
import com.example.session10_roomlocaldbpart1.data.database.AppDatabase
import com.example.session11_ucp2.repository.LocalRepositoryDosen
import com.example.session11_ucp2.repository.LocalRepositoryMataKuliah
import com.example.session11_ucp2.repository.RepositoryDosen
import com.example.session11_ucp2.repository.RepositoryMataKuliah

// Interface untuk menyuntikkan dependensi RepositoryDosen dan RepositoryMataKuliah
interface InterfaceContainerApp {
    val repositoryDosen: RepositoryDosen
    val repositoryMataKuliah: RepositoryMataKuliah
}

class ContainerApp(private val context: Context) : InterfaceContainerApp {
    // Menggunakan lazy untuk inisialisasi repositoryDosen
    override val repositoryDosen: RepositoryDosen by lazy {
        LocalRepositoryDosen(AppDatabase.getDatabase(context).dosenDao())
    }

    // Menggunakan lazy untuk inisialisasi repositoryMataKuliah
    override val repositoryMataKuliah: RepositoryMataKuliah by lazy {
        LocalRepositoryMataKuliah(AppDatabase.getDatabase(context).mataKuliahDao())
    }
}

