package com.example.session11_ucp2.ui.view.dosen

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
import com.example.session11_ucp2.ui.viewmodel.dosen.ListDosenViewModel
import com.example.session11_ucp2.ui.viewmodel.dosen.PenyediaViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.lazy.items
import com.example.session11_ucp2.data.entity.Dosen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListDosenView(
    viewModel: ListDosenViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddDosenClick: () -> Unit,
) {
    val listDosen = viewModel.listDosen.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home Dosen") },
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
        }
    }
}

