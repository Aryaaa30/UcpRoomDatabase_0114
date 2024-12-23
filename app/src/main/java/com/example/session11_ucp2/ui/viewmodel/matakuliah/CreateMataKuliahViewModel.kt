package com.example.session11_ucp2.ui.viewmodel.matakuliah

data class MataKuliahUIState(
    val mataKuliahEvent: MataKuliahEvent = MataKuliahEvent(),
    val isEntryValid: FormErrorStateMataKuliah = FormErrorStateMataKuliah(),
    val snackBarMessage: String? = null
)

data class MataKuliahEvent(
    val kode: String = "",
    val nama: String = "",
    val sks: Int = 0,
    val semester: String = "",
    val jenis: String = "",
    val dosenPengampu: String = ""
)

