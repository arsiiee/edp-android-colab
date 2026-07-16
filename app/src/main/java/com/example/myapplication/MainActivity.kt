@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.ProfileTheme
import com.example.myapplication.ui.theme.StatusOnline

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfileTheme {
                ProfileScreen()
            }
        }
    }
}

// =============================================================================
// SCREEN — Regions A-G assembled inside a Scaffold (Part B of the lab)
// =============================================================================

@Composable
fun ProfileScreen() {
    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { /* TODO: add contact */ },
                icon = { Icon(Icons.Default.Add, contentDescription = null) },
                text = { Text("Add") },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeroHeader()
            Spacer(Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ActionButtonsRow()
                Spacer(Modifier.height(24.dp))
                StatsCard()
                Spacer(Modifier.height(16.dp))
                ContactInfoCard()
                Spacer(Modifier.height(96.dp)) // clears the extended FAB
            }
        }
    }
}

// ---- Region A: Top bar -----------------------------------------------------
@Composable
private fun ProfileTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "My Profile",
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(onClick = { /* TODO: handle back */ }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = { /* TODO: show overflow menu */ }) {
                Icon(Icons.Default.MoreVert, contentDescription = "More options")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        modifier = Modifier.background(Color.Transparent)
    )
}

// ---- Hero header: gradient band behind the top bar + avatar + name --------
// Pulling the top bar, avatar, and name into one gradient-backed block with
// rounded bottom corners reads as a single "hero" section rather than a
// plain list, and gives the primary/primaryContainer colors real presence
// instead of confining them to a thin app-bar strip.
@Composable
private fun HeroHeader() {
    val gradient = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primaryContainer,
            MaterialTheme.colorScheme.surface
        )
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            .background(gradient),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileTopBar()
        Spacer(Modifier.height(8.dp))
        AvatarWithBadge()
        Spacer(Modifier.height(14.dp))
        NameAndRole(name = "Rwyne Zoe", role = "Game Developer")
        Spacer(Modifier.height(20.dp))
    }
}

// ---- Region B: Avatar + status badge ---------------------------------------
@Composable
private fun AvatarWithBadge() {
    Box(modifier = Modifier.size(104.dp)) {
        // Modifier order matters here: clip -> background -> border.
        // clip first so the background/border get cut to the circle;
        // background before border so the border strokes the circle's
        // outer edge instead of being painted over by a rectangular fill.
        // A slightly thicker, primary-colored ring plus a soft surface
        // "halo" gap gives the avatar the look of sitting on top of the
        // gradient rather than being flush with it.
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface)
                .padding(4.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.pfp),
                contentDescription = "Profile photo",
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .border(2.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )
        }

        // Status dot, pinned to the bottom-end corner of the Box.
        Box(
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.BottomEnd)
                .clip(CircleShape)
                .background(StatusOnline)
                .border(3.dp, MaterialTheme.colorScheme.surface, CircleShape)
        )
    }
}

// ---- Region C: Name & role --------------------------------------------------
@Composable
private fun NameAndRole(name: String, role: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = role,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

// ---- Region D: Action buttons ----------------------------------------------
@Composable
private fun ActionButtonsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Button(
            onClick = { /* TODO: message action */ },
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
            shape = RoundedCornerShape(14.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 2.dp,
                pressedElevation = 0.dp
            )
        ) {
            Icon(Icons.Default.Email, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Text("Message", style = MaterialTheme.typography.labelLarge)
        }

        OutlinedButton(
            onClick = { /* TODO: call action */ },
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
            shape = RoundedCornerShape(14.dp),
            border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.secondary),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Text("Call", style = MaterialTheme.typography.labelLarge)
        }
    }
}

// ---- Region E: Stats card ---------------------------------------------------
@Composable
private fun StatsCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            StatItem(number = "128", label = "Posts")
            StatDivider()
            StatItem(number = "2.4K", label = "Followers")
            StatDivider()
            StatItem(number = "312", label = "Following")
        }
    }
}

@Composable
private fun StatDivider() {
    Box(
        modifier = Modifier
            .height(32.dp)
            .width(1.dp)
            .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
    )
}

@Composable
private fun StatItem(number: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = number,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(2.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

// ---- Region F: Contact info card --------------------------------------------
@Composable
private fun ContactInfoCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            ContactRow(icon = Icons.Default.Email, text = "rwyne.zoe@gmail.com")
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)
            )
            ContactRow(icon = Icons.Default.Phone, text = "+63 900 000 0000")
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)
            )
            ContactRow(icon = Icons.Default.LocationOn, text = "Cagayan de Oro, Philippines")
        }
    }
}

@Composable
private fun ContactRow(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon sits inside a soft tinted "chip" circle instead of floating
        // bare on the card — small touch that makes each row feel designed
        // rather than a raw icon+text pairing.
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.size(18.dp)
            )
        }
        Spacer(Modifier.width(12.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

// ---- Modifier-order demo (Objective 4) --------------------------------------
// Deliberately shows how the SAME two modifiers, reordered, change the
// padding/background relationship. Not part of the mockup — kept here for
// the lab's "observe and explain" requirement.
@Composable
private fun ModifierOrderDemo() {
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        // padding() BEFORE background(): the padding pushes the background
        // inward too, so the surface color shows through as a gap around
        // the tinted box.
        Box(
            modifier = Modifier
                .size(80.dp)
                .padding(12.dp)
                .background(MaterialTheme.colorScheme.primaryContainer)
        )

        // background() BEFORE padding(): the background fills the full
        // 80.dp box, and the padding only pushes CONTENT inward, leaving
        // colored space visible at the edges.
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(12.dp)
        )
    }
}

// =============================================================================
// PREVIEWS — render without an emulator/device (Part C)
// =============================================================================

@Preview(showBackground = true, name = "Profile - Light")
@Composable
fun ProfileScreenLightPreview() {
    ProfileTheme(darkTheme = false) {
        ProfileScreen()
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Profile - Dark"
)
@Composable
fun ProfileScreenDarkPreview() {
    ProfileTheme(darkTheme = true) {
        ProfileScreen()
    }
}