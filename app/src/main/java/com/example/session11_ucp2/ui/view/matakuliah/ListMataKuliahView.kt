package com.example.session11_ucp2.ui.view.matakuliah

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.session11_ucp2.ui.viewmodel.matakuliah.ListMataKuliahViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.lazy.items
import com.example.session11_ucp2.data.entity.MataKuliah
import com.example.session11_ucp2.ui.viewmodel.dosen.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListMataKuliahView(
    viewModel: ListMataKuliahViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddMataKuliahClick: () -> Unit
) {
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
                EmptyState()
            } else {
                MataKuliahList(listMataKuliah.value)
            }
        }
    }
}

@Composable
fun EmptyState() {
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
