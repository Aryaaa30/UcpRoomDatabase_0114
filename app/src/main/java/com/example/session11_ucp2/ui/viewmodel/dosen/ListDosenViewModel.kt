package com.example.session11_ucp2.ui.viewmodel.dosen

import Dosen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.session11_ucp2.repository.RepositoryDosen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListDosenViewModel(private val repository: RepositoryDosen) : ViewModel() {
    private val _listDosen = MutableStateFlow<List<Dosen>>(emptyList())
    val listDosen: StateFlow<List<Dosen>> = _listDosen

    init {
        viewModelScope.launch {
            val dosenList = repository.getAllDosen()
            _listDosen.value = dosenList
        }
    }
}


