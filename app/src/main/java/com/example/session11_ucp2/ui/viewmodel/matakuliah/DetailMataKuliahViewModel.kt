package com.example.session11_ucp2.ui.viewmodel.matakuliah

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.session11_ucp2.data.entity.MataKuliah
import com.example.session11_ucp2.repository.RepositoryMataKuliah
import com.example.session11_ucp2.ui.navigation.DestinasiDetailMataKuliah
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailMataKuliahViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMataKuliah: RepositoryMataKuliah,
) : ViewModel() {
    private val _kodeMataKuliah: String = checkNotNull(savedStateHandle[DestinasiDetailMataKuliah.KODE_MATA_KULIAH])

    val detailUiState: StateFlow<DetailMataKuliahUiState> = repositoryMataKuliah.getDetailMataKuliah(_kodeMataKuliah)
        .filterNotNull()
        .map { mataKuliah ->
            DetailMataKuliahUiState(
                detailUiEvent = mataKuliah.toDetailUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailMataKuliahUiState(isLoading = true))
            delay(600)
        }
        .catch { exception ->
            emit(
                DetailMataKuliahUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = exception.message ?: "Terjadi kesalahan",
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailMataKuliahUiState(
                isLoading = true,
            ),
        )

    fun deleteMataKuliah() {
        val mataKuliahEntity = detailUiState.value.detailUiEvent.toMataKuliahEntity()
        viewModelScope.launch {
            repositoryMataKuliah.deleteMataKuliah(mataKuliahEntity)
        }
    }
}

data class DetailMataKuliahUiState(
    val detailUiEvent: MataKuliahEvent = MataKuliahEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == MataKuliahEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != MataKuliahEvent()
}

// Memindahkan data dari entity ke UI
fun MataKuliah.toDetailUiEvent(): MataKuliahEvent {
    return MataKuliahEvent(
        kode = kode,
        nama = nama,
        sks = sks,
        semester = semester,
        jenis = jenis,
        dosenPengampu = dosenPengampu
    )
}
