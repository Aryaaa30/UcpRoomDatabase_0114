package com.example.session11_ucp2.ui.viewmodel.dosen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.session11_ucp2.data.entity.Dosen
import com.example.session11_ucp2.repository.RepositoryDosen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ListDosenViewModel(
    private val repositoryDosen: RepositoryDosen
) : ViewModel() {
    // State untuk daftar dosen
    private val _listDosen = MutableStateFlow<List<Dosen>>(emptyList())
    val listDosen: StateFlow<List<Dosen>> get() = _listDosen

    init {
        fetchListDosen()
    }

    private fun fetchListDosen() {
        viewModelScope.launch {
            repositoryDosen.getAllDosen() // Ambil Flow<List<Dosen>> dari repository
                .collect { data ->
                    _listDosen.value = data // Perbarui state dengan data dari repository
                }
        }
    }
}
