package com.example.session11_ucp2.ui.viewmodel.matakuliah

import MataKuliah
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.session11_ucp2.repository.RepositoryMataKuliah
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateMataKuliahViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMataKuliah: RepositoryMataKuliah
) : ViewModel() {

    var updateUIState by mutableStateOf(MataKuliahUIState())
        private set

    private val _kodeMataKuliah: String = checkNotNull(savedStateHandle[DestinasiEdit.KODE_MATA_KULIAH])

    init {
        viewModelScope.launch {
            updateUIState = repositoryMataKuliah.getDetailMataKuliah(_kodeMataKuliah)
                .filterNotNull()
                .first()
                .toUIState()
        }
    }

    fun updateState(mataKuliahEvent: MataKuliahEvent) {
        updateUIState = updateUIState.copy(
            mataKuliahEvent = mataKuliahEvent,
        )
    }

    fun validateFields(): Boolean {
        val event = updateUIState.mataKuliahEvent
        val errorState = FormErrorStateMataKuliah(
            kode = if(event.kode.isNotEmpty()) null else "Kode Mata Kuliah Tidak Boleh Kosong",
            nama = if(event.nama.isNotEmpty()) null else "Nama Mata Kuliah Tidak Boleh Kosong",
            sks = if(event.sks > 0) null else "SKS Tidak Boleh Kosong",
            semester = if(event.semester.isNotEmpty()) null else "Semester Tidak Boleh Kosong",
            jenis = if(event.jenis.isNotEmpty()) null else "Jenis Mata Kuliah Tidak Boleh Kosong",
            dosenPengampu = if(event.dosenPengampu.isNotEmpty()) null else "Dosen Pengampu Tidak Boleh Kosong"
        )
        updateUIState = updateUIState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun updateData() {
        val currentEvent = updateUIState.mataKuliahEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryMataKuliah.updateMataKuliah(currentEvent.toMataKuliahEntity())
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data berhasil diupdate",
                        mataKuliahEvent = MataKuliahEvent(),
                        isEntryValid = FormErrorStateMataKuliah()
                    )
                    println("snackBarMessage diatur: ${updateUIState.snackBarMessage}")
                } catch (e: Exception) {
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data gagal diupdate"
                    )
                }
            }
        } else {
            updateUIState = updateUIState.copy(
                snackBarMessage = "Data gagal diupdate"
            )
        }
    }

    fun resetSnackBarMessage() {
        updateUIState = updateUIState.copy(snackBarMessage = null)
    }
}

fun MataKuliah.toUIState(): MataKuliahUIState = MataKuliahUIState(
    mataKuliahEvent = this.toDetailUiEvent(),
)

