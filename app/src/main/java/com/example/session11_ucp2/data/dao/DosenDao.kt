package com.example.session11_ucp2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.example.session11_ucp2.data.entity.Dosen

@Dao
interface DosenDao {
    @Insert
    suspend fun insertDosen(dosen: Dosen)

    @Query("SELECT * FROM dosen")
    fun getAllDosen(): Flow<List<Dosen>>

}
