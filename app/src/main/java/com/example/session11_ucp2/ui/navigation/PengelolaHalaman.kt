package com.example.session11_ucp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.session11_ucp2.ui.view.MenuView
import com.example.session11_ucp2.ui.view.dosen.CreateDosenView
import com.example.session11_ucp2.ui.view.dosen.ListDosenView
import com.example.session11_ucp2.ui.view.matakuliah.CreateMataKuliahView
import com.example.session11_ucp2.ui.view.matakuliah.DetailMataKuliahView
import com.example.session11_ucp2.ui.view.matakuliah.UpdateMataKuliahView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route // Start dari halaman utama
    ) {
        // MenuView
        composable(route = DestinasiHome.route) {
            MenuView(
                onDosenClick = {
                    navController.navigate(DestinasiCreateDosen.route)
                },
                onMataKuliahClick = {
                    navController.navigate(DestinasiCreateMataKuliah.route)
                }
            )
        }

        composable(route = DestinasiListDosen.route) {
            ListDosenView(
                onAddDosenClick = { navController.navigate(DestinasiCreateDosen.route) }
            )
        }
        composable(route = DestinasiCreateDosen.route) {
            CreateDosenView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.navigate(DestinasiListDosen.route) }
            )
        }

        // CreateMataKuliahView
        composable(route = DestinasiCreateMataKuliah.route) {
            CreateMataKuliahView(
                onBack = { navController.popBackStack() },
                onNavigate = {
                    navController.popBackStack()
                }
            )
        }

        // DetailMataKuliahView
        composable(
            route = DestinasiDetailMataKuliah.route,
            arguments = listOf(
                navArgument(DestinasiDetailMataKuliah.KODE_MATA_KULIAH) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val kodeMataKuliah =
                backStackEntry.arguments?.getString(DestinasiDetailMataKuliah.KODE_MATA_KULIAH)
            kodeMataKuliah?.let { kode ->
                DetailMataKuliahView(
                    onBack = { navController.popBackStack() },
                    onEditClick = {
                        navController.navigate(DestinasiEdit.routeWithArgs(kode))
                    },
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        // UpdateMataKuliahView
        composable(
            route = DestinasiEdit.route,
            arguments = listOf(
                navArgument(DestinasiEdit.KODE_MATA_KULIAH) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val kodeMataKuliah =
                backStackEntry.arguments?.getString(DestinasiEdit.KODE_MATA_KULIAH)
            kodeMataKuliah?.let { kode ->
                UpdateMataKuliahView(
                    onBack = { navController.popBackStack() },
                    onNavigate = { navController.popBackStack() }
                )
            }
        }
    }
}
