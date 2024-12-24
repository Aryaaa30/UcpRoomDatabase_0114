package com.example.session11_ucp2.ui.view.matakuliah

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.monitoringapplication.ui.costumwidget.CostumTopAppBar
import com.example.session11_ucp2.ui.navigation.AlamatNavigasi
import com.example.session11_ucp2.ui.viewmodel.dosen.PenyediaViewModel
import com.example.session11_ucp2.ui.viewmodel.matakuliah.CreateMataKuliahViewModel
import com.example.session11_ucp2.ui.viewmodel.matakuliah.FormErrorStateMataKuliah
import com.example.session11_ucp2.ui.viewmodel.matakuliah.MataKuliahEvent
import com.example.session11_ucp2.ui.viewmodel.matakuliah.MataKuliahUIState
import kotlinx.coroutines.launch

object DestinasiCreateMataKuliah : AlamatNavigasi {
    override val route: String = "create_matakuliah"
}

@Composable
fun CreateMataKuliahView(
    onBack: () -> Unit,
    onNavigate: () -> Unit, // Navigasi ke halaman ListMataKuliah setelah sukses
    modifier: Modifier = Modifier,
    viewModel: CreateMataKuliahViewModel = viewModel(factory = PenyediaViewModel.Factory)
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
                judul = "Tambah Mata Kuliah"
            )
            InsertBodyMataKuliah(
                uiState = uiState,
                onValueChange = { updatedEvent -> viewModel.updateState(updatedEvent) },
                onClick = {
                    viewModel.saveData(onSuccess = {
                        onNavigate() // Navigasi ke halaman ListMataKuliah setelah sukses
                    })
                }
            )
        }
    }
}

@Composable
fun InsertBodyMataKuliah(
    modifier: Modifier = Modifier,
    onValueChange: (MataKuliahEvent) -> Unit,
    uiState: MataKuliahUIState,
    onClick: () -> Unit // Properti ini hanya akan menangani aksi simpan
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormMataKuliah(
            mataKuliahEvent = uiState.mataKuliahEvent,
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
fun FormMataKuliah(
    mataKuliahEvent: MataKuliahEvent = MataKuliahEvent(),
    onValueChange: (MataKuliahEvent) -> Unit,
    errorState: FormErrorStateMataKuliah = FormErrorStateMataKuliah(),
    modifier: Modifier = Modifier
) {
    val jenisKuliah = listOf("Wajib", "Pilihan")

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        // Kode
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mataKuliahEvent.kode,
            onValueChange = {
                onValueChange(mataKuliahEvent.copy(kode = it))
            },
            label = { Text("Kode Mata Kuliah") },
            isError = errorState.kode != null, // Tampilkan error jika tidak valid
            placeholder = { Text("Masukkan Kode Mata Kuliah") },
        )
        if (errorState.kode != null) {
            Text(
                text = errorState.kode,
                color = Color.Red,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }

        // Nama
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mataKuliahEvent.nama,
            onValueChange = {
                onValueChange(mataKuliahEvent.copy(nama = it))
            },
            label = { Text("Nama Mata Kuliah") },
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan Nama Mata Kuliah") },
        )
        if (errorState.nama != null) {
            Text(
                text = errorState.nama,
                color = Color.Red,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }

        // SKS
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mataKuliahEvent.sks.toString(),
            onValueChange = {
                val sksValue = it.toIntOrNull()
                if (sksValue != null) {
                    onValueChange(mataKuliahEvent.copy(sks = sksValue))
                } else {
                    onValueChange(mataKuliahEvent.copy(sks = 0)) // Atur default jika input tidak valid
                }
            },
            label = { Text("SKS") },
            isError = errorState.sks != null,
            placeholder = { Text("Masukkan SKS") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        if (errorState.sks != null) {
            Text(
                text = errorState.sks,
                color = Color.Red,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }

        // Semester
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mataKuliahEvent.semester,
            onValueChange = {
                onValueChange(mataKuliahEvent.copy(semester = it))
            },
            label = { Text("Semester") },
            isError = errorState.semester != null,
            placeholder = { Text("Masukkan Semester") },
        )
        if (errorState.semester != null) {
            Text(
                text = errorState.semester,
                color = Color.Red,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }

        // Jenis
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Jenis Mata Kuliah")
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            jenisKuliah.forEach { jk ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        selected = mataKuliahEvent.jenis == jk,
                        onClick = {
                            onValueChange(mataKuliahEvent.copy(jenis = jk))
                        },
                    )
                    Text(
                        text = jk,
                    )
                }
            }
        }
        if (errorState.jenis != null) {
            Text(
                text = errorState.jenis,
                color = Color.Red,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }

        // Dosen Pengampu
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mataKuliahEvent.dosenPengampu,
            onValueChange = {
                onValueChange(mataKuliahEvent.copy(dosenPengampu = it))
            },
            label = { Text("Dosen Pengampu") },
            isError = errorState.dosenPengampu != null,
            placeholder = { Text("Masukkan Dosen Pengampu") },
        )
        if (errorState.dosenPengampu != null) {
            Text(
                text = errorState.dosenPengampu,
                color = Color.Red,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }
    }
}
