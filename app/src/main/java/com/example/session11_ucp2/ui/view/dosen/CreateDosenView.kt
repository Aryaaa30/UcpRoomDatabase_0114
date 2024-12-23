package com.example.session11_ucp2.ui.view.dosen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.session11_ucp2.ui.navigation.AlamatNavigasi
import com.example.session11_ucp2.ui.viewmodel.dosen.CreateDosenViewModel

object DestinasiCreateDosen : AlamatNavigasi {
    override val route: String = "create_dosen"
}

@Composable
fun CreateDosenView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    //viewModel: CreateDosenViewModel = viewModel(factory = PenyediaViewModel.Factory)
){

}
