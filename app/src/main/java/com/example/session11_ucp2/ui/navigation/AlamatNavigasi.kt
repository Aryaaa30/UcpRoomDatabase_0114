package com.example.session11_ucp2.ui.navigation

interface AlamatNavigasi {
    val route: String
}

object DestinasiCreateDosen : AlamatNavigasi {
    override val route: String = "create_dosen"
}

object DestinasiCreateMataKuliah : AlamatNavigasi {
    override val route: String = "create_mata_kuliah"
}

object DestinasiEdit : AlamatNavigasi {
    const val KODE_MATA_KULIAH = "kode_mata_kuliah"
    override val route: String = "edit_mata_kuliah/{$KODE_MATA_KULIAH}"
}

object DestinasiDetailMataKuliah : AlamatNavigasi {
    const val KODE_MATA_KULIAH = "kode_mata_kuliah"
    override val route: String = "detail_mata_kuliah/{$KODE_MATA_KULIAH}"
}

