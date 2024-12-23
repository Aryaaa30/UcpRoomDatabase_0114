package com.example.session11_ucp2.repository

import com.example.session11_ucp2.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

interface RepositoryMataKuliah {
    suspend fun insertMataKuliah(mataKuliah: MataKuliah)
    fun getAllMataKuliah(): Flow<List<MataKuliah>>
    suspend fun updateMataKuliah(mataKuliah: MataKuliah)
    suspend fun deleteMataKuliah(mataKuliah: MataKuliah)
    fun getDetailMataKuliah(kode: String): Flow<MataKuliah>
}