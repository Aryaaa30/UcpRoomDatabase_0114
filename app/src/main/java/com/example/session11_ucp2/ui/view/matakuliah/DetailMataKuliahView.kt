package com.example.session11_ucp2.ui.view.matakuliah

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.session11_ucp2.data.entity.MataKuliah
import com.example.session11_ucp2.ui.viewmodel.matakuliah.DetailMataKuliahViewModel
import com.example.session11_ucp2.ui.viewmodel.matakuliah.DetailMataKuliahUiState
import com.example.session11_ucp2.ui.viewmodel.matakuliah.MataKuliahEvent
import com.example.session11_ucp2.ui.viewmodel.matakuliah.toMataKuliahEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMataKuliahView(
    modifier: Modifier = Modifier,
    viewModel: DetailMataKuliahViewModel = viewModel(),
    onBack: () -> Unit = { },
    onEditClick: (String) -> Unit = { },
    onDeleteClick: () -> Unit = { }
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Mata Kuliah") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                modifier = modifier
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEditClick(viewModel.detailUiState.value.detailUiEvent.kode)
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Mata Kuliah"
                )
            }
        }
    ) { innerPadding ->
        val detailUiState by viewModel.detailUiState.collectAsState()

        BodyDetailMataKuliah(
            modifier = Modifier.padding(innerPadding),
            detailUiState = detailUiState,
            onDeleteClick = {
                viewModel.deleteMataKuliah()
                onDeleteClick()
            }
        )
    }
}

@Composable
fun BodyDetailMataKuliah(
    modifier: Modifier = Modifier,
    detailUiState: DetailMataKuliahUiState = DetailMataKuliahUiState(),
    onDeleteClick: () -> Unit = { }
) {
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        when {
            detailUiState.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center)) // Tampilkan loading
            }
            detailUiState.isError -> {
                Text(
                    text = "Terjadi kesalahan: ${detailUiState.errorMessage}",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            detailUiState.isUiEventNotEmpty -> {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    ItemDetailMataKuliah(
                        mataKuliah = detailUiState.detailUiEvent.toMataKuliahEntity(),
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Button(
                        onClick = {
                            deleteConfirmationRequired = true
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Delete")
                    }
                }
            }
            else -> {
                Box(
                    modifier = modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Data tidak ditemukan")
                }
            }
        }
    }

    if (deleteConfirmationRequired) {
        DeleteConfirmationDialog(
            onDeleteConfirm = {
                deleteConfirmationRequired = false
                onDeleteClick()
            },
            onDeleteCancel = { deleteConfirmationRequired = false },
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun ItemDetailMataKuliah(
    modifier: Modifier = Modifier,
    mataKuliah: MataKuliah
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ComponentDetailMataKuliah(judul = "Kode Mata Kuliah", isinya = mataKuliah.kode)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMataKuliah(judul = "Nama Mata Kuliah", isinya = mataKuliah.nama)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMataKuliah(judul = "SKS", isinya = mataKuliah.sks.toString())
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMataKuliah(judul = "Semester", isinya = mataKuliah.semester)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMataKuliah(judul = "Jenis", isinya = mataKuliah.jenis)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailMataKuliah(judul = "Dosen Pengampu", isinya = mataKuliah.dosenPengampu)
        }
    }
}

@Composable
fun ComponentDetailMataKuliah(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya, fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { /* Do nothing */ },
        title = { Text("Delete Data") },
        text = { Text("Apakah anda yakin ingin menghapus data?") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        }
    )
}
