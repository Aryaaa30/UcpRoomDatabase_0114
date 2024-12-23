package com.example.session11_ucp2

import android.app.Application
import com.example.session11_ucp2.dependenciesinjection.ContainerApp

class AcademicApp : Application() {
    // Menyimpan instance dari ContainerApp untuk menyuntikkan dependensi
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        // Inisialisasi ContainerApp
        containerApp = ContainerApp(this)
    }
}