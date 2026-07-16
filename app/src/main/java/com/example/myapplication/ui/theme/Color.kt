package com.example.myapplication.ui.theme

import androidx.compose.ui.graphics.Color

// ---- Brand palette --------------------------------------------------------
// Only six roles were given per mode; every other scheme role in Theme.kt
// is derived from these so text/icons stay legible on every surface.

// Light
val LightPrimary            = Color(0xFF73411F)
val LightOnPrimary          = Color(0xFFFFFFFF)
val LightPrimaryContainer   = Color(0xFFE9C9C8)
val LightOnPrimaryContainer = Color(0xFF452815)
val LightSecondary          = Color(0xFFB6885D)
val LightOnSecondary        = Color(0xFFFFFFFF)
val LightSurface            = Color(0xFFFFFBFF)
val LightOnSurface          = Color(0xFF201A19)
val LightOnSurfaceVariant   = Color(0xFF5A4D4C)
val LightOutline            = Color(0xFF8D7371)

// Dark
val DarkPrimary            = Color(0xFFE0A3A0)
val DarkOnPrimary          = Color(0xFF511313)
val DarkPrimaryContainer   = Color(0xFF651817)
val DarkOnPrimaryContainer = Color(0xFFFFDAD8)
val DarkSecondary          = Color(0xFFD49B99)
val DarkOnSecondary        = Color(0xFF5C1614)
val DarkSurface            = Color(0xFF1A1110)
val DarkOnSurface          = Color(0xFFEDE0DE)
val DarkOnSurfaceVariant   = Color(0xFFC9B8B7)
val DarkOutline            = Color(0xFFA48C8A)

// Non-role accent for the "online" status dot on the avatar badge. Not part
// of the M3 role system (nothing ever sits "on" it), so a literal is fine
// here — every other color in the screen must come from the theme.
val StatusOnline = Color(0xFF4CAF50)