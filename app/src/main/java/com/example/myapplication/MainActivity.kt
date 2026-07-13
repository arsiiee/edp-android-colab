package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.test.espresso.base.Default
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.ui.graphics.Brush

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        BusinessCard()
                    }
                }
            }
        }
    }
}

@Composable
fun BusinessCard() {
    Column(Modifier.fillMaxSize().background(
        Brush.verticalGradient(
        colors = listOf(Color(0xFFFBF8F5), Color(0xFFEFE7E0))
    )),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Image(painterResource(R.drawable.unnamed),
            contentDescription = "Profile photo",
            contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter,
            modifier = Modifier.size(120.dp)
                .clip(CircleShape)
                .border(2.dp, Color(0xFF771C1B), CircleShape))
        Spacer(Modifier.height(16.dp))
        Text("Arweyne Zoe A Salcedo.", fontSize = 24.sp,
            fontWeight = FontWeight.Bold, color = Color(0xFF771C1B))
        Text("Developer", fontSize = 16.sp,
            color = Color.DarkGray)
        Spacer(Modifier.height(24.dp))
// a reusable contact row
        ContactRow(
            Icons.Default.Phone,
            "+63 900 000 0000")
        ContactRow(Icons.Default.Email,
            "az@salcedo.com")
    }
}

@Composable
fun ContactRow(icon: ImageVector, label: String){
    Row(Modifier.padding(vertical = 6.dp)
        .clickable { /* TODO action */ },
        verticalAlignment =
            Alignment.CenterVertically) {
        Icon(icon, null, tint = Color(0xFF771C1B))
        Spacer(Modifier.width(8.dp))
        Text(label) }
}

@Preview(name = "Card - Light", showBackground = true, widthDp = 360)
@Composable
fun BusinessCardPreview() {
BusinessCard()
}
