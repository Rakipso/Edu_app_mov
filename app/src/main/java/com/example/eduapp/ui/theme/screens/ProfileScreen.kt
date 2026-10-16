package com.example.eduapp.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eduapp.R
import com.example.eduapp.ui.theme.EduBg
import com.example.eduapp.ui.theme.EduBlue
import com.example.eduapp.ui.theme.EduChipBg
import com.example.eduapp.ui.theme.EduChipText
import com.example.eduapp.ui.theme.EduRed
import com.example.eduapp.ui.theme.EduTextMuted
import com.example.eduapp.ui.theme.EduTextPrimary
import com.example.eduapp.ui.theme.EduTextSecondary
import com.example.eduapp.ui.theme.EduappTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onEditProfileClick: () -> Unit = {},
    onNotificationsClick: () -> Unit = {},
    onHelpClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    var coursesCount by remember { mutableIntStateOf(3) }
    var certificatesCount by remember { mutableIntStateOf(4) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(EduBg)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Tarjeta con información del perfil
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Avatar Alex
                Image(
                    painter = painterResource(id = R.drawable.avatar_alex),
                    contentDescription = "Foto de perfil de Alex",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(76.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Alex Mendoza",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = EduTextPrimary
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = "alex@ejemplo.com",
                    fontSize = 13.sp,
                    color = EduTextSecondary
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Fila de Estadísticas
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            StatCard(
                value = coursesCount.toString(),
                label = "Cursos",
                modifier = Modifier.weight(1f)
            )

            StatCard(
                value = certificatesCount.toString(),
                label = "Certificados",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Opciones del Menú de Configuración
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                ProfileOptionItem(
                    title = "Editar perfil",
                    icon = Icons.Outlined.Badge,
                    onClick = onEditProfileClick
                )

                HorizontalDivider(
                    color = EduBg,
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                ProfileOptionItem(
                    title = "Notificaciones",
                    icon = Icons.Outlined.Notifications,
                    onClick = onNotificationsClick
                )

                HorizontalDivider(
                    color = EduBg,
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                ProfileOptionItem(
                    title = "Ayuda",
                    icon = Icons.Outlined.HelpOutline,
                    onClick = onHelpClick
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Botón Cerrar Sesión
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .clickable { onLogoutClick() }
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.Logout,
                contentDescription = null,
                tint = EduRed,
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Cerrar sesión",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = EduRed
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun StatCard(
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp, horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = EduBlue
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = label,
                fontSize = 12.sp,
                color = EduTextSecondary
            )
        }
    }
}

@Composable
fun ProfileOptionItem(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(EduChipBg),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = EduChipText,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Text(
            text = title,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = EduTextPrimary,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = EduTextMuted,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    EduappTheme {
        ProfileScreen()
    }
}
