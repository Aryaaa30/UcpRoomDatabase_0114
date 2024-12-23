package com.example.session11_ucp2.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.session11_ucp2.ui.view.MenuView

enum class Halaman{
    Menu,
    CreateDosen,
    ListDosen,
    ListMataKuliah
}

@Composable
fun PengelolaHalaman(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = Halaman.Menu.name,
        modifier = modifier.padding()
    ) {
        // MenuView
        composable(route = Halaman.Menu.name) {
            MenuView(
                onMahasiswaClick = { navController.navigate(Halaman.CreateDosen.name) },
                onDosenClick = { navController.navigate(Halaman.ListDosen.name) },
                onMataKuliahClick = { navController.navigate(Halaman.ListMataKuliah.name) }
            )
        }
    }
}

