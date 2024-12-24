package com.example.session11_ucp2.ui.view.dosen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.session11_ucp2.data.entity.Dosen
import com.example.session11_ucp2.ui.viewmodel.dosen.ListDosenViewModel
import com.example.session11_ucp2.ui.viewmodel.dosen.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListDosenView(
    viewModel: ListDosenViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddDosenClick: () -> Unit,
    onBackClick: () -> Unit // Tambahkan onBackClick untuk navigasi kembali
) {
    val listDosen = viewModel.listDosen.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Home Dosen",
                        modifier = Modifier.fillMaxWidth(), // Membuat teks mengisi seluruh lebar
                        style = MaterialTheme.typography.titleLarge, // Sesuaikan dengan gaya MaterialTheme
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center // Posisikan teks di tengah
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) { // Tambahkan tombol kembali
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onAddDosenClick() }) {
                Text("+")
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            if (listDosen.value.isEmpty()) {
                EmptyState()
            } else {
                DosenList(listDosen.value)
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
        Text("No Dosen available")
    }
}

@Composable
fun DosenList(dosenList: List<Dosen>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(dosenList) { dosen ->
            DosenItem(dosen)
        }
    }
}

@Composable
fun DosenItem(dosen: Dosen) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = dosen.nama, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = dosen.nidn, style = MaterialTheme.typography.bodyMedium) // Menampilkan NIDN dosen
        }
    }
}
