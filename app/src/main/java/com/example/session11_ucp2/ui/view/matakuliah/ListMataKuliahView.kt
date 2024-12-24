package com.example.session11_ucp2.ui.view.matakuliah

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.session11_ucp2.data.entity.MataKuliah
import com.example.session11_ucp2.ui.viewmodel.matakuliah.ListMataKuliahViewModel
import com.example.session11_ucp2.ui.viewmodel.dosen.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListMataKuliahView(
    viewModel: ListMataKuliahViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddMataKuliahClick: () -> Unit
) {
    // Observasi state untuk daftar mata kuliah
    val listMataKuliah = viewModel.listMataKuliah.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home Mata Kuliah") },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onAddMataKuliahClick() }) {
                Text("+")
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            if (listMataKuliah.value.isEmpty()) {
                EmptyStateMataKuliah()
            } else {
                MataKuliahList(listMataKuliah.value)
            }
        }
    }
}

@Composable
fun EmptyStateMataKuliah() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("No Mata Kuliah available")
    }
}

@Composable
fun MataKuliahList(mataKuliahList: List<MataKuliah>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(mataKuliahList) { mataKuliah ->
            MataKuliahItem(mataKuliah)
        }
    }
}

@Composable
fun MataKuliahItem(mataKuliah: MataKuliah) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = mataKuliah.nama, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Kode: ${mataKuliah.kode}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "SKS: ${mataKuliah.sks}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
