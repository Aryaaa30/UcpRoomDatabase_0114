package com.example.session11_ucp2.ui.navigation

interface AlamatNavigasi {
    val route: String
}

object DestinasiHome {
    const val route = "home"
}

object DestinasiListDosen : AlamatNavigasi {
    override val route: String = "list_dosen"
}

object DestinasiCreateDosen : AlamatNavigasi {
    override val route: String = "create_dosen"
}

object DestinasiCreateMataKuliah {
    const val route = "create_matakuliah"
}

object DestinasiDetailMataKuliah {
    const val KODE_MATA_KULIAH = "kodeMataKuliah"
    const val route = "detail_matakuliah/{$KODE_MATA_KULIAH}"
    val routeWithArgs: (String) -> String = { kode ->
        "detail_matakuliah/$kode"
    }
}

object DestinasiEdit {
    const val KODE_MATA_KULIAH = "kodeMataKuliah"
    const val route = "edit_matakuliah/{$KODE_MATA_KULIAH}"
    val routeWithArgs: (String) -> String = { kode ->
        "edit_matakuliah/$kode"
    }
}
