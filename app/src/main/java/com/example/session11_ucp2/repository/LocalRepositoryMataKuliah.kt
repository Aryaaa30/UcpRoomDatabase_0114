package com.example.session11_ucp2.repository

import MataKuliah
import MataKuliahDao
import kotlinx.coroutines.flow.Flow

class LocalRepositoryMataKuliah(private val mataKuliahDao: MataKuliahDao) : RepositoryMataKuliah {
    override suspend fun insertMataKuliah(mataKuliah: MataKuliah) {
        mataKuliahDao.insertMataKuliah(mataKuliah)
    }

    override fun getAllMataKuliah(): Flow<List<MataKuliah>> {
        return mataKuliahDao.getAllMataKuliah()
    }

    override suspend fun updateMataKuliah(mataKuliah: MataKuliah) {
        mataKuliahDao.updateMataKuliah(mataKuliah)
    }

    override suspend fun deleteMataKuliah(mataKuliah: MataKuliah) {
        mataKuliahDao.deleteMataKuliah(mataKuliah)
    }

    override fun getDetailMataKuliah(kode: String): Flow<MataKuliah> {
        return mataKuliahDao.getDetailMataKuliah(kode)
    }
}