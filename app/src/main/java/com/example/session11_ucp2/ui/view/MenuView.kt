package com.example.session11_ucp2.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.session11_ucp2.ui.navigation.AlamatNavigasi

object DestinasiHome : AlamatNavigasi {
    override val route = "home"
}

@Composable
fun MenuView(
    onDosenClick: () -> Unit,
    onMataKuliahClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF42A5F5),
                        Color(0xFF0D47A1),
                        Color(0xFF6A1B9A) // Ungu muda di bagian atas
                    )
                )
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween, // Pisahkan ruang antara konten utama dan footer
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Konten Utama
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), // Gunakan weight agar footer tetap berada di bawah
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Header dengan Logo dan Judul
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.padding(start = 16.dp))
                Column {
                    Text(
                        text = "Universitas Muhammadiyah Yogyakarta",
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Unggul dan Islami",
                        color = Color.Black,
                        fontWeight = FontWeight.Light
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp)) // Space antara logo dan konten utama

            // Konten Utama dengan Judul
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topEnd = 15.dp, topStart = 15.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(
                        text = "Hello Teknologi Informasi",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFD32F2F), // Warna merah agar mencolok
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Menu Pilihan",
                        fontWeight = FontWeight.Bold,
                        fontSize = 19.sp,
                    )
                    Text(
                        text = "Pilih menu yang kamu inginkan",
                        fontWeight = FontWeight.Light,
                    )

                    Spacer(modifier = Modifier.height(20.dp)) // Space sebelum item menu

                    // Tombol Menu
                    MenuButton(text = "Dosen", color = Color(0xFFFFB74D), onClick = onDosenClick)
                    Spacer(modifier = Modifier.height(20.dp))
                    MenuButton(text = "Mata Kuliah", color = Color(0xFF66BB6A), onClick = onMataKuliahClick)
                }
                // Footer
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                ) {
                    Divider(
                        color = Color.Black,
                        thickness = 1.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                    Text(
                        text = "© 2024 Universitas Muhammadiyah Yogyakarta",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.Black
                    )
                    Text(
                        text = "Powered by Teknologi Informasi",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.Black
                    )
                }
            }
        }
    }
}


@Composable
fun MenuButton(text: String, color: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 16.dp)
            .clickable { onClick() }
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(8.dp))  // Shadow effect on card
            .background(color = color, shape = RoundedCornerShape(8.dp)),  // Card with background color and shape
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = color)  // Background color of Card
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = text,
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
