package com.example.session11_ucp2.ui.viewmodel.dosen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.session11_ucp2.data.entity.Dosen
import com.example.session11_ucp2.repository.RepositoryDosen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListDosenViewModel(private val repository: RepositoryDosen) : ViewModel() { // Menggunakan nama parameter repository
    private val _listDosen = MutableStateFlow<List<Dosen>>(emptyList())
    val listDosen: Flow<List<Dosen>> = repository.getAllDosen()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        viewModelScope.launch {
            repository.getAllDosen().collect { dosenList -> // Menggunakan repository (parameter konstruktor)
                println("Daftar dosen: $dosenList") // Tambahkan log untuk debugging
                _listDosen.value = dosenList
            }
        }
    }
}
