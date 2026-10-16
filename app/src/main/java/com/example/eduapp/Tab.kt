package com.example.eduapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.School
import androidx.compose.ui.graphics.vector.ImageVector

enum class Tab(
    val label: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector = icon
) {
    INICIO("Inicio", Icons.Outlined.Home, Icons.Filled.Home),
    CURSOS("Cursos", Icons.Outlined.School, Icons.Filled.School),
    PERFIL("Perfil", Icons.Outlined.Person, Icons.Filled.Person)
}