package com.example.eduapp.ui.theme.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Assignment
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eduapp.ui.theme.EduBg
import com.example.eduapp.ui.theme.EduBlue
import com.example.eduapp.ui.theme.EduBlueLight
import com.example.eduapp.ui.theme.EduGreen
import com.example.eduapp.ui.theme.EduGreenBg
import com.example.eduapp.ui.theme.EduTextMuted
import com.example.eduapp.ui.theme.EduTextPrimary
import com.example.eduapp.ui.theme.EduTextSecondary
import com.example.eduapp.ui.theme.EduTrack
import com.example.eduapp.ui.theme.EduappTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onContinueClick: () -> Unit = {},
    onTasksClick: () -> Unit = {},
    onClassesClick: () -> Unit = {}
) {
    var progress by remember { mutableStateOf(0.60f) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(EduBg)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        // Saludo de bienvenida
        Text(
            text = "¡Hola, Alex!",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = EduTextPrimary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Bienvenido a tu aula virtual",
            fontSize = 14.sp,
            color = EduTextSecondary
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Tarjeta Curso Actual
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "MI CURSO ACTUAL",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = EduTextMuted,
                        letterSpacing = 0.8.sp
                    )
                    Text(
                        text = "${(progress * 100).toInt()}%",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = EduBlue
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Fundamentos de Diseño",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = EduTextPrimary
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = "Continúa donde lo dejaste",
                    fontSize = 13.sp,
                    color = EduTextSecondary
                )

                Spacer(modifier = Modifier.height(14.dp))

                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = EduBlue,
                    trackColor = EduTrack
                )

                Spacer(modifier = Modifier.height(18.dp))

                Button(
                    onClick = onContinueClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = EduBlue),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Continuar",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Continuar curso",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Opciones secundarias
        OptionCard(
            title = "Mis Tareas",
            subtitle = "Actividades pendientes",
            icon = Icons.Outlined.Assignment,
            iconBg = EduBlueLight,
            iconTint = EduBlue,
            onClick = onTasksClick
        )

        Spacer(modifier = Modifier.height(12.dp))

        OptionCard(
            title = "Mis Clases",
            subtitle = "Horarios y sesiones",
            icon = Icons.Outlined.School,
            iconBg = EduGreenBg,
            iconTint = EduGreen,
            onClick = onClassesClick
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun OptionCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    iconBg: Color,
    iconTint: Color,
    onClick: () -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = EduTextPrimary
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    color = EduTextSecondary,
                    fontSize = 12.sp
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = EduTextMuted,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    EduappTheme {
        HomeScreen()
    }
}
