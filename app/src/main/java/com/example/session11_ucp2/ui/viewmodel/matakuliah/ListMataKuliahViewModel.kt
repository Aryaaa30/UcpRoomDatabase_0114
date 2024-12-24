package com.example.session11_ucp2.ui.viewmodel.matakuliah

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.session11_ucp2.data.entity.MataKuliah
import com.example.session11_ucp2.repository.RepositoryMataKuliah
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListMataKuliahViewModel(
    private val repositoryMataKuliah: RepositoryMataKuliah
) : ViewModel() {
    // State untuk daftar mata kuliah
    private val _listMataKuliah = MutableStateFlow<List<MataKuliah>>(emptyList())
    val listMataKuliah: StateFlow<List<MataKuliah>> get() = _listMataKuliah

    init {
        fetchListMataKuliah()
    }

    private fun fetchListMataKuliah() {
        viewModelScope.launch {
            repositoryMataKuliah.getAllMataKuliah() // Ambil Flow<List<MataKuliah>> dari repository
                .collect { data ->
                    _listMataKuliah.value = data // Perbarui state dengan data dari repository
                }
        }
    }
}
