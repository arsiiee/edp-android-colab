package com.example.myapplication

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.test.espresso.base.Default
import androidx.compose.material3.Typography


// Maroon Colors
val Maroon = Color(0xFF800000)
val LightMaroon = Color(0xFFB03060)
val Cream = Color(0xFFFFF5EE)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {

    Scaffold(
        containerColor = Cream,

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "My Profile",
                        color = Color.White
                    )
                },

                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Menu, null, tint = Color.White)
                    }
                },

                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.MoreVert, null, tint = Color.White)
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Maroon
                )
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = Maroon,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, null)
            }
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
                .fillMaxSize(),

            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Box(
                modifier = Modifier.size(130.dp),
                contentAlignment = Alignment.Center
            ) {

                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(LightMaroon)
                        .border(4.dp, Maroon, CircleShape),

                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        "AS",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineMedium
                    )

                }

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(22.dp)
                        .clip(CircleShape)
                        .background(Color.Green)
                )

            }

            Text(
                "ARWEYNE ZOE A. SALCEDO",
                style = MaterialTheme.typography.headlineSmall,
                color = Maroon
            )

            Text(
                "BSIT 3-A",
                color = Color.DarkGray
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Button(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Maroon
                    )
                ) {
                    Text("Message")
                }

                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Maroon
                    )
                ) {
                    Text("Follow")
                }

            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    InfoRow(
                        Icons.Default.Person,
                        "Full Name",
                        "ARWEYNE ZOE A. SALCEDO"
                    )

                    InfoRow(
                        Icons.Default.Person,
                        "Course",
                        "Bachelor of Science in Information Technology"
                    )

                    InfoRow(
                        Icons.Default.Person,
                        "Section",
                        "BSIT 3-A"
                    )

                    InfoRow(
                        Icons.Default.Phone,
                        "Mobile Number",
                        "+63 936 200 8050"
                    )

                    InfoRow(
                        Icons.Default.Email,
                        "Email Address",
                        "azsalcedo64143@liceo.edu.ph"
                    )

                }

            }

        }

    }

}

@Composable
fun InfoRow(
    icon: ImageVector,
    label: String,
    value: String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Maroon
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {

            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )

            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge
            )

        }

    }

}
private val LightColors = lightColorScheme(
    primary = Color(0xFF6650a4),
    secondary = Color(0xFF625b71),
    tertiary = Color(0xFF7D5260)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFD0BCFF),
    secondary = Color(0xFFCCC2DC),
    tertiary = Color(0xFFEFB8C8)
)

@Preview(showBackground = true, name = "Profile - Light")
@Composable
fun LightPreview() {
    ProfileTheme(darkTheme = false) {
        ProfileScreen()
    }
}

@Composable
fun ProfileTheme(darkTheme: Boolean, content: @Composable () -> Unit) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun DarkPreview() {
    ProfileTheme(darkTheme = true) {
        ProfileScreen()
    }
}

