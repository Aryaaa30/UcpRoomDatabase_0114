package com.example.session11_ucp2.ui.view.dosen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.session11_ucp2.ui.navigation.AlamatNavigasi
import com.example.session11_ucp2.ui.viewmodel.dosen.CreateDosenViewModel
import com.example.session11_ucp2.ui.viewmodel.dosen.DosenEvent
import com.example.session11_ucp2.ui.viewmodel.dosen.FormErrorStateDosen

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
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan Nama") },
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )

        // NIDN
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dosenEvent.nidn,
            onValueChange = {
                onValueChange(dosenEvent.copy(nidn = it))
            },
            label = { Text("NIDN") },
            isError = errorState.nidn != null,
            placeholder = { Text("Masukkan NIDN") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(
            text = errorState.nidn ?: "",
            color = Color.Red
        )

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
        Text(
            text = errorState.jenisKelamin ?: "",
            color = Color.Red
        )
    }
}