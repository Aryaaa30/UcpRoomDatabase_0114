package com.example.session11_ucp2.repository

import Dosen
import DosenDao
import kotlinx.coroutines.flow.Flow

class LocalRepositoryDosen(private val dosenDao: DosenDao) : RepositoryDosen {
    override suspend fun insertDosen(dosen: Dosen) {
        dosenDao.insertDosen(dosen)
    }

    override fun getAllDosen(): Flow<List<Dosen>> {
        return dosenDao.getAllDosen()
    }
}