package com.example.session11_ucp2.ui.viewmodel.matakuliah

import MataKuliah
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.session11_ucp2.repository.RepositoryMataKuliah
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListMataKuliahViewModel(private val repository: RepositoryMataKuliah) : ViewModel() {
    private val _listMataKuliah = MutableStateFlow<List<MataKuliah>>(emptyList())
    val listMataKuliah: StateFlow<List<MataKuliah>> = _listMataKuliah

    init {
        viewModelScope.launch {
            // Mengumpulkan data dari Flow dan menetapkannya ke _listMataKuliah
            repository.getAllMataKuliah().collect { mataKuliahList ->
                _listMataKuliah.value = mataKuliahList
            }
        }
    }
}
