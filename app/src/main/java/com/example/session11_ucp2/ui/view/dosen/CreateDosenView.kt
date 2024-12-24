package com.example.session11_ucp2.ui.view.dosen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.monitoringapplication.ui.costumwidget.CostumTopAppBar
import com.example.session11_ucp2.ui.navigation.AlamatNavigasi
import com.example.session11_ucp2.ui.viewmodel.dosen.CreateDosenViewModel
import com.example.session11_ucp2.ui.viewmodel.dosen.DosenEvent
import com.example.session11_ucp2.ui.viewmodel.dosen.DosenUIState
import com.example.session11_ucp2.ui.viewmodel.dosen.FormErrorStateDosen
import com.example.session11_ucp2.ui.viewmodel.dosen.PenyediaViewModel
import kotlinx.coroutines.launch

object DestinasiCreateDosen : AlamatNavigasi {
    override val route: String = "create_dosen"
}

@Composable
fun CreateDosenView(
    onBack: () -> Unit,
    onNavigate: () -> Unit, // Navigasi ke halaman ListDosen setelah sukses
    modifier: Modifier = Modifier,
    viewModel: CreateDosenViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Memantau pesan Snackbar dari ViewModel
    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold(
        modifier = Modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            CostumTopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Dosen"
            )
            InsertBodyDosen(
                uiState = uiState,
                onValueChange = { updatedEvent -> viewModel.updateState(updatedEvent) },
                onClick = {
                    viewModel.saveData(onSuccess = {
                        onNavigate() // Navigasi ke halaman ListDosen setelah sukses
                    })
                }
            )
        }
    }
}

@Composable
fun InsertBodyDosen(
    modifier: Modifier = Modifier,
    onValueChange: (DosenEvent) -> Unit,
    uiState: DosenUIState,
    onClick: () -> Unit // Properti ini hanya akan menangani aksi simpan
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormDosen(
            dosenEvent = uiState.dosenEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick, // Panggilan onClick langsung
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan")
        }
    }
}

@Composable
fun FormDosen(
    dosenEvent: DosenEvent = DosenEvent(),
    onValueChange: (DosenEvent) -> Unit,
    errorState: FormErrorStateDosen = FormErrorStateDosen(),
    modifier: Modifier = Modifier
) {
    val jenisKelamin = listOf("Laki-Laki", "Perempuan")

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        // Nama
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dosenEvent.nama,
            onValueChange = {
                onValueChange(dosenEvent.copy(nama = it))
            },
            label = { Text("Nama") },
            isError = errorState.nama != null, // Tampilkan error jika tidak valid
            placeholder = { Text("Masukkan Nama") },
        )
        if (errorState.nama != null) {
            Text(
                text = errorState.nama,
                color = Color.Red,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }

        // NIDN
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dosenEvent.nidn,
            onValueChange = {
                onValueChange(dosenEvent.copy(nidn = it))
            },
            label = { Text("NIDN") },
            isError = errorState.nidn != null, // Tampilkan error jika tidak valid
            placeholder = { Text("Masukkan NIDN") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        if (errorState.nidn != null) {
            Text(
                text = errorState.nidn,
                color = Color.Red,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }

        // Jenis Kelamin
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Jenis Kelamin")
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            jenisKelamin.forEach { jk ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        selected = dosenEvent.jenisKelamin == jk,
                        onClick = {
                            onValueChange(dosenEvent.copy(jenisKelamin = jk))
                        },
                    )
                    Text(
                        text = jk,
                    )
                }
            }
        }
        if (errorState.jenisKelamin != null) {
            Text(
                text = errorState.jenisKelamin,
                color = Color.Red,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }
    }
}
