package com.example.session11_ucp2.ui.viewmodel.dosen

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.session11_ucp2.AcademicApp
import com.example.session11_ucp2.ui.viewmodel.matakuliah.CreateMataKuliahViewModel
import com.example.session11_ucp2.ui.viewmodel.matakuliah.ListMataKuliahViewModel

object PenyediaViewModel{
    val Factory = viewModelFactory{
        initializer{
            CreateDosenViewModel(
                academicApp().containerApp.repositoryDosen


            )
        }
        initializer {
            CreateMataKuliahViewModel(
                academicApp().containerApp.repositoryMataKuliah
            )
        }
        initializer {
            ListDosenViewModel(
                academicApp().containerApp.repositoryDosen // Repository untuk daftar dosen
            )
        }
        initializer {
            ListMataKuliahViewModel(
                academicApp().containerApp.repositoryMataKuliah
            )
        }
    }
}

fun CreationExtras.academicApp(): AcademicApp {
    return this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
            as? AcademicApp ?: throw IllegalStateException("Application is not an instance of AcademicApp")
}
