package com.example.session11_ucp2.ui.viewmodel.dosen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.session11_ucp2.data.entity.Dosen
import com.example.session11_ucp2.repository.RepositoryDosen
import kotlinx.coroutines.launch

class CreateDosenViewModel(private val repositoryDosen: RepositoryDosen) : ViewModel(){
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

        println("Validation result: ${errorState.isValid()}") // Tambahkan log
        return errorState.isValid()
    }


    fun saveData(onSuccess: () -> Unit) {
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryDosen.insertDosen(uiState.dosenEvent.toDosenEntity())
                    println("Data berhasil disimpan") // Tambahkan log
                    uiState = uiState.copy(
                        snackBarMessage = "Data Berhasil Disimpan",
                        dosenEvent = DosenEvent(),
                        isEntryValid = FormErrorStateDosen()
                    )
                    onSuccess() // Navigasi setelah sukses
                } catch (e: Exception) {
                    println("Gagal menyimpan data: ${e.message}") // Tambahkan log error
                    uiState = uiState.copy(
                        snackBarMessage = "Data Gagal Disimpan"
                    )
                }
            }
        } else {
            println("Validasi gagal. Tidak menyimpan data.") // Tambahkan log
            uiState = uiState.copy(
                snackBarMessage = "Input Tidak Valid. Periksa Kembali Data Anda!!"
            )
        }
    }




    // Reset snackbar message
    fun resetSnackBarMessage() {
        uiState = uiState.copy(snackBarMessage = null)
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
