package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.geometry.Offset
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.foundation.isSystemInDarkTheme
import android.content.res.Configuration
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle

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
    val isDark = isSystemInDarkTheme()
    val gradientColors = if (isDark)
        listOf(Color(0xFF2A0808), Color(0xFF5C1013), Color(0xFF7A2E1A))
    else
        listOf(Color(0xFFFFF3E6), Color(0xFFF3C9A0), Color(0xFFD1512F))
    val nameColor = if (isDark) Color(0xFFFFF8EE) else Color(0xFF4A0F0E)
    val subtitleColor = if (isDark) Color(0xFFF3C9A0) else Color(0xFF9C3D1E)
    val borderColor = if (isDark) Color(0xFFF3E4C8) else Color(0xFF771C1B)

    Box(Modifier.fillMaxSize()){
        Canvas(Modifier.fillMaxSize()) {
            val dotColor = Color.White.copy(alpha = 0.25f)
            val spacing = 32.dp.toPx()
            val radius = 4.dp.toPx()
            var y = 0f
            while (y < size.height){
                var x = 0f
                while (x < size.width ) {
                    drawCircle(dotColor, radius = 1f, center = Offset(x, y))
                    x += spacing
                }
                y += spacing
            }
        }
    }
    Column(Modifier.fillMaxSize().background(
        brush = Brush.verticalGradient(
            colors = listOf(Color(0xFF5C1013), Color(0xFFD1512F), Color(0xFFF3C9A0))
        )
    ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Image(painterResource(R.drawable.unnamed),
            contentDescription = "Profile photo",
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter,
            modifier = Modifier.size(132.dp)
                .shadow(8.dp, CircleShape)
                .clip(CircleShape)
                .border(3.dp, Color(0xFFF3E4C8), CircleShape))
        Spacer(Modifier.height(20.dp))
        Text("Arweyne Zoe A. Salcedo", fontSize = 28.sp,
            fontWeight = FontWeight.Bold, color = Color(0xFFFFF8EE),
            letterSpacing = 0.4.sp, style = TextStyle(
                shadow = Shadow(color = Color.Black.copy(alpha = 0.25f), offset = Offset(0f, 2f), blurRadius = 6f)
            ))
        Text("SOFTWARE DEVELOPER", fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFFF3C9A0),
            letterSpacing = 3.sp)
        Spacer(Modifier.height(32.dp).width(60.dp)
            .background(Color.White.copy(alpha = 0.3f)))
        Spacer(Modifier.height(24.dp))
        // a reusable contact row
        ContactRow(Icons.Default.Phone,"+63 900 000 0000")
        Spacer(Modifier.height(4.dp))
        ContactRow(Icons.Default.Email, "az@salcedo.com")
        Spacer(Modifier.height(4.dp))
        ContactRow(Icons.Default.LocationOn, "Cagayan de Oro City, PH")
    }

}
@Composable
fun ContactRow(icon: ImageVector, label: String){
    val isDark = isSystemInDarkTheme()
    val iconColor = if (isDark) Color(0xFFF3E4C8) else Color(0xFF771C1B)
    val chipColor = if (isDark) Color(0xFFF3E4C8).copy(alpha = 0.15f) else Color(0xFF771C1B).copy(alpha = 0.1f)
    val textColor = if (isDark) Color(0xFFFFF3E6) else Color(0xFF3A2321)

    Row(Modifier.padding(vertical = 8.dp)
        .clickable { /* TODO action */ },
        verticalAlignment =
            Alignment.CenterVertically) {
        Box(Modifier.size(32.dp).clip(CircleShape).background(chipColor),
        contentAlignment = Alignment.Center) {
            Icon(icon, null, tint = iconColor, modifier = Modifier.size(16.dp))
        }
        Spacer(Modifier.width(10.dp))
        Text(label, fontSize = 15.sp, color = Color(0xFFFFF3E6)) }
}

@Preview(name = "Card - Light", showBackground = true, widthDp = 360)
@Composable
fun BusinessCardPreview() {
BusinessCard()
}

@Preview(name = "Card - Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BusinessCardDarkPreview() {
    BusinessCard()
}
