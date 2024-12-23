package com.example.session11_ucp2.ui.viewmodel.dosen

import Dosen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.session11_ucp2.repository.RepositoryDosen

class DosenViewModel(private val repositoryDosen: RepositoryDosen) : ViewModel(){
    var uiState by mutableStateOf(DosenUIState())

    fun updateState(dosenEvent: DosenEvent) {
        uiState = uiState.copy(
            dosenEvent = dosenEvent
        )
    }

    private fun validateFields(): Boolean {
        val event = uiState.dosenEvent
        val errorState = FormErrorStateDosen(
            nidn = if (event.nidn.isNotEmpty()) null else "NIDN Tidak Boleh Kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama Tidak Boleh Kosong",
            jenisKelamin = if (event.jenisKelamin.isNotEmpty()) null else "Jenis Kelamin Tidak Boleh Kosong"
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }


}

data class DosenUIState(
    val dosenEvent: DosenEvent = DosenEvent(),
    val isEntryValid: FormErrorStateDosen = FormErrorStateDosen(),
    val snackBarMessage: String? = null
)

data class FormErrorStateDosen(
    val nidn: String? = null,
    val nama: String? = null,
    val jenisKelamin: String? = null
) {
    fun isValid(): Boolean {
        return nidn == null && nama == null && jenisKelamin == null
    }
}

data class DosenEvent(
    val nidn: String = "",
    val nama: String = "",
    val jenisKelamin: String = ""
)

fun DosenEvent.toDosenEntity(): Dosen = Dosen(
    nidn = nidn,
    nama = nama,
    jenisKelamin = jenisKelamin
)
