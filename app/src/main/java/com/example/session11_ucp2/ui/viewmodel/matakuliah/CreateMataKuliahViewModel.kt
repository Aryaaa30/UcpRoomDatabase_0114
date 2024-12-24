package com.example.session11_ucp2.ui.viewmodel.matakuliah

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.session11_ucp2.data.entity.MataKuliah
import com.example.session11_ucp2.repository.RepositoryMataKuliah
import com.example.session11_ucp2.ui.viewmodel.dosen.DosenEvent
import com.example.session11_ucp2.ui.viewmodel.dosen.FormErrorStateDosen
import com.example.session11_ucp2.ui.viewmodel.dosen.toDosenEntity
import kotlinx.coroutines.launch

class CreateMataKuliahViewModel(private val repositoryMataKuliah: RepositoryMataKuliah) : ViewModel() {
    var uiState by mutableStateOf(MataKuliahUIState())

    fun updateState(mataKuliahEvent: MataKuliahEvent) {
        uiState = uiState.copy(
            mataKuliahEvent = mataKuliahEvent
        )
    }

    private fun validateFields(): Boolean {
        val event = uiState.mataKuliahEvent
        val errorState = FormErrorStateMataKuliah(
            kode = if (event.kode.isNotEmpty()) null else "Kode Tidak Boleh Kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama Tidak Boleh Kosong",
            sks = if (event.sks > 0) null else "SKS Harus Lebih Dari 0",
            semester = if (event.semester.isNotEmpty()) null else "Semester Tidak Boleh Kosong",
            jenis = if (event.jenis.isNotEmpty()) null else "Jenis Tidak Boleh Kosong",
            dosenPengampu = if (event.dosenPengampu.isNotEmpty()) null else "Dosen Pengampu Tidak Boleh Kosong"
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData(onSuccess: () -> Unit) {
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryMataKuliah.insertMataKuliah(uiState.mataKuliahEvent.toMataKuliahEntity())
                    println("Data berhasil disimpan") // Tambahkan log
                    uiState = uiState.copy(
                        snackBarMessage = "Data Berhasil Disimpan",
                        mataKuliahEvent = MataKuliahEvent(),
                        isEntryValid = FormErrorStateMataKuliah()
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

data class MataKuliahUIState(
    val mataKuliahEvent: MataKuliahEvent = MataKuliahEvent(),
    val isEntryValid: FormErrorStateMataKuliah = FormErrorStateMataKuliah(),
    val snackBarMessage: String? = null
)

data class FormErrorStateMataKuliah(
    val kode: String? = null,
    val nama: String? = null,
    val sks: String? = null,
    val semester: String? = null,
    val jenis: String? = null,
    val dosenPengampu: String? = null
) {
    fun isValid(): Boolean {
        return kode == null && nama == null && sks == null && semester == null && jenis == null && dosenPengampu == null
    }
}

data class MataKuliahEvent(
    val kode: String = "",
    val nama: String = "",
    val sks: Int = 0,
    val semester: String = "",
    val jenis: String = "",
    val dosenPengampu: String = ""
)

fun MataKuliahEvent.toMataKuliahEntity(): MataKuliah = MataKuliah(
    kode = kode,
    nama = nama,
    sks = sks,
    semester = semester,
    jenis = jenis,
    dosenPengampu = dosenPengampu
)
