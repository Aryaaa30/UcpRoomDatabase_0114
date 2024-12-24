package com.example.session11_ucp2.repository

import com.example.session11_ucp2.data.entity.Dosen
import kotlinx.coroutines.flow.Flow


interface RepositoryDosen {
    suspend fun insertDosen(dosen: Dosen)
    fun getAllDosen(): Flow<List<Dosen>>
}

