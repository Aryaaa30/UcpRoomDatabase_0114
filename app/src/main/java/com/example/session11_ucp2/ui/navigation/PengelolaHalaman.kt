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

enum class Routes(val route: String) {
    HOME("home"),
    CREATE_DOSEN("create_dosen"),
    LIST_DOSEN("list_dosen"),
    CREATE_MATAKULIAH("create_matakuliah"),
    DETAIL_MATAKULIAH("detail_mata_kuliah/{kodeMataKuliah}"),
    EDIT_MATAKULIAH("edit_mata_kuliah/{kodeMataKuliah}");

    fun withArgs(vararg args: String): String {
        var updatedRoute = route
        args.forEachIndexed { index, arg ->
            updatedRoute = updatedRoute.replaceFirst("{${index}}", arg)
        }
        return updatedRoute
    }
}



    @Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME.route
    ) {
        // MenuView
        composable(route = Routes.HOME.route) {
            MenuView(
                onDosenClick = {
                    navController.navigate(Routes.CREATE_DOSEN.route)
                },
                onMataKuliahClick = {
                    navController.navigate(Routes.CREATE_MATAKULIAH.route)
                }
            )
        }

        // ListDosenView
        composable(route = Routes.LIST_DOSEN.route) {
            ListDosenView(
                onAddDosenClick = { navController.navigate(Routes.CREATE_DOSEN.route) }
            )
        }

        // CreateDosenView
        composable(route = Routes.CREATE_DOSEN.route) {
            CreateDosenView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.navigate(Routes.LIST_DOSEN.route) }
            )
        }

        // CreateMataKuliahView
        composable(route = Routes.CREATE_MATAKULIAH.route) {
            CreateMataKuliahView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() }
            )
        }

        // DetailMataKuliahView
        composable(
            route = Routes.DETAIL_MATAKULIAH.route,
            arguments = listOf(
                navArgument("kodeMataKuliah") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val kodeMataKuliah = backStackEntry.arguments?.getString("kodeMataKuliah")
            kodeMataKuliah?.let { kode ->
                DetailMataKuliahView(
                    onBack = { navController.popBackStack() },
                    onEditClick = {
                        if (kode.isNotEmpty()){
                            navController.navigate(Routes.EDIT_MATAKULIAH.withArgs(kode))
                        }else {
                            println("Kode Mata Kuliah kosong, navigasi dibatalkan.")}
                    },
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        // UpdateMataKuliahView
        composable(
            route = Routes.EDIT_MATAKULIAH.route,
            arguments = listOf(
                navArgument("kodeMataKuliah") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val kodeMataKuliah = backStackEntry.arguments?.getString("kodeMataKuliah")
            kodeMataKuliah?.let { kode ->
                UpdateMataKuliahView(
                    onBack = { navController.popBackStack() },
                    onNavigate = { navController.popBackStack() }
                )
            }
        }
    }
}



