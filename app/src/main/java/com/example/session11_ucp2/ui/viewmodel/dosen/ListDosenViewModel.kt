package com.example.session11_ucp2.ui.viewmodel.dosen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.session11_ucp2.data.entity.Dosen
import com.example.session11_ucp2.repository.RepositoryDosen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListDosenViewModel(private val repository: RepositoryDosen) : ViewModel() {
    private val _listDosen = MutableStateFlow<List<Dosen>>(emptyList())
    val listDosen: StateFlow<List<Dosen>> = _listDosen

    init {
        viewModelScope.launch {
            // Mengumpulkan data dari Flow dan menetapkannya ke _listDosen
            repository.getAllDosen().collect { dosenList ->
                _listDosen.value = dosenList
            }
        }
    }
}
