@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
// THEME — colors, typography, shapes, and the ProfileTheme wrapper.
// (Part A of the lab: all defined in one place, no hard-coded colors below.)
// =============================================================================

// ---- Brand palette --------------------------------------------------------
// Only six roles were given per mode; every other scheme role is derived
// from them so text/icons stay legible on every surface.

// Light
private val LightPrimary            = Color(0xFF771C1B)
private val LightOnPrimary          = Color(0xFFFFFFFF)
private val LightPrimaryContainer   = Color(0xFFE9C9C8)
private val LightOnPrimaryContainer = Color(0xFF2C0A09)
private val LightSecondary          = Color(0xFF9E4744)
private val LightOnSecondary        = Color(0xFFFFFFFF)
private val LightSurface            = Color(0xFFFFFBFF)
private val LightOnSurface          = Color(0xFF201A19)
private val LightOnSurfaceVariant   = Color(0xFF5A4D4C)
private val LightOutline            = Color(0xFF8D7371)

// Dark
private val DarkPrimary            = Color(0xFFE0A3A0)
private val DarkOnPrimary          = Color(0xFF511313)
private val DarkPrimaryContainer   = Color(0xFF651817)
private val DarkOnPrimaryContainer = Color(0xFFFFDAD8)
private val DarkSecondary          = Color(0xFFD49B99)
private val DarkOnSecondary        = Color(0xFF5C1614)
private val DarkSurface            = Color(0xFF1A1110)
private val DarkOnSurface          = Color(0xFFEDE0DE)
private val DarkOnSurfaceVariant   = Color(0xFFC9B8B7)
private val DarkOutline            = Color(0xFFA48C8A)

// Non-role accent for the "online" status dot on the avatar badge. Not part
// of the M3 role system (nothing ever sits "on" it), so a literal is fine
// here — every other color in the screen must come from the theme.
private val StatusOnline = Color(0xFF4CAF50)

private val LightColors = lightColorScheme(
    primary = LightPrimary,
    onPrimary = LightOnPrimary,
    primaryContainer = LightPrimaryContainer,
    onPrimaryContainer = LightOnPrimaryContainer,
    secondary = LightSecondary,
    onSecondary = LightOnSecondary,
    surface = LightSurface,
    onSurface = LightOnSurface,
    onSurfaceVariant = LightOnSurfaceVariant,
    outline = LightOutline,
    background = LightSurface,
    onBackground = LightOnSurface
)

private val DarkColors = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = DarkOnPrimary,
    primaryContainer = DarkPrimaryContainer,
    onPrimaryContainer = DarkOnPrimaryContainer,
    secondary = DarkSecondary,
    onSecondary = DarkOnSecondary,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    onSurfaceVariant = DarkOnSurfaceVariant,
    outline = DarkOutline,
    background = DarkSurface,
    onBackground = DarkOnSurface
)

// Title bar -> titleLarge bold; name -> headlineSmall; role/labels -> bodyMedium.
private val ProfileTypography = androidx.compose.material3.Typography(
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 30.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    )
)

// Cards use the medium shape (16.dp); buttons stay at the M3 default (small)
// since `small` is left un-overridden here.
private val ProfileShapes = Shapes(
    medium = RoundedCornerShape(16.dp)
)

/**
 * App-wide theme. Defaults to following the system setting, but exposes
 * [darkTheme] explicitly so both @Preview functions below can force a mode
 * without needing an emulator or device.
 */
@Composable
fun ProfileTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = ProfileTypography,
        shapes = ProfileShapes,
        content = content
    )
}

// =============================================================================
// SCREEN — Regions A-G assembled inside a Scaffold (Part B of the lab)
// =============================================================================

@Composable
fun ProfileScreen() {
    Scaffold(
        topBar = { ProfileTopBar() },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* TODO: add contact */ }) {
                Icon(Icons.Default.Add, contentDescription = "Add contact")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(20.dp))
            AvatarWithBadge()
            Spacer(Modifier.height(12.dp))
            NameAndRole(name = "Rwyne Salcedo", role = "Game Developer")
            Spacer(Modifier.height(20.dp))
            ActionButtonsRow()
            Spacer(Modifier.height(20.dp))
            StatsCard()
            Spacer(Modifier.height(16.dp))
            ContactInfoCard()
            Spacer(Modifier.height(88.dp)) // clears the FAB when scrolled to bottom
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
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

// ---- Region B: Avatar + status badge ---------------------------------------
@Composable
private fun AvatarWithBadge() {
    Box(modifier = Modifier.size(96.dp)) {
        // Modifier order matters here: clip -> background -> border.
        // clip first so the background/border get cut to the circle;
        // background before border so the border strokes the circle's
        // outer edge instead of being painted over by a rectangular fill.
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Profile photo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        // Status dot, pinned to the bottom-end corner of the Box.
        Box(
            modifier = Modifier
                .size(18.dp)
                .align(Alignment.BottomEnd)
                .clip(CircleShape)
                .background(StatusOnline)
                .border(2.dp, MaterialTheme.colorScheme.surface, CircleShape)
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
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = { /* TODO: message action */ },
            modifier = Modifier.weight(1f)
        ) {
            Icon(Icons.Default.Email, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(6.dp))
            Text("Message")
        }

        OutlinedButton(
            onClick = { /* TODO: call action */ },
            modifier = Modifier.weight(1f),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(6.dp))
            Text("Call")
        }
    }
}

// ---- Region E: Stats card ---------------------------------------------------
@Composable
private fun StatsCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatItem(number = "128", label = "Posts")
            StatItem(number = "2.4K", label = "Followers")
            StatItem(number = "312", label = "Following")
        }
    }
}

@Composable
private fun StatItem(number: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = number,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
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
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ContactRow(icon = Icons.Default.Email, text = "ada.lovelace@example.com")
            Spacer(Modifier.height(12.dp))
            ContactRow(icon = Icons.Default.Phone, text = "+63 900 000 0000")
            Spacer(Modifier.height(12.dp))
            ContactRow(icon = Icons.Default.LocationOn, text = "Cagayan de Oro, Philippines")
        }
    }
}

@Composable
private fun ContactRow(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.width(8.dp))
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