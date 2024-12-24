package com.example.session11_ucp2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "mataKuliah")
data class MataKuliah(
    @PrimaryKey
    val kode: String,
    val nama: String,
    val sks: Int,
    val semester: String,
    val jenis: String,
    val dosenPengampu: String
)

