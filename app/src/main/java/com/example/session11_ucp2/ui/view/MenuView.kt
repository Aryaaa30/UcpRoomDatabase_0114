package com.example.session11_ucp2.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuView(
    onMahasiswaClick: () -> Unit,
    onDosenClick: () -> Unit,
    onMataKuliahClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Logo dan Header

        Text(
            text = "Hallo",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = "Teknologi Informasi",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Menu
        MenuButton(text = "Mahasiswa", color = Color(0xFF87CEEB), onClick = onMahasiswaClick)
        Spacer(modifier = Modifier.height(16.dp))
        MenuButton(text = "Dosen", color = Color(0xFF6A5ACD), onClick = onDosenClick)
        Spacer(modifier = Modifier.height(16.dp))
        MenuButton(text = "Mata Kuliah", color = Color(0xFFFFD700), onClick = onMataKuliahClick)
    }
}

@Composable
fun MenuButton(text: String, color: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = color) // Mengganti backgroundColor
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = text, fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

