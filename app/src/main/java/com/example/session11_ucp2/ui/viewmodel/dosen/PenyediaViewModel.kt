package com.example.session11_ucp2.ui.viewmodel.dosen

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.session11_ucp2.AcademicApp

object PenyediaViewModel{
    val Factory = viewModelFactory{
        initializer{
            CreateDosenViewModel(
                academicApp().containerApp.repositoryDosen
            )
        }
    }
}

fun CreationExtras.academicApp(): AcademicApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AcademicApp)