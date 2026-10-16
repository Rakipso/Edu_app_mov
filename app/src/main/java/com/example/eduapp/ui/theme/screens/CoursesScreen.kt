package com.example.eduapp.ui.theme.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.DesktopMac
import androidx.compose.material.icons.outlined.Terminal
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.eduapp.ui.theme.EduBorder
import com.example.eduapp.ui.theme.EduChipBg
import com.example.eduapp.ui.theme.EduChipText
import com.example.eduapp.ui.theme.EduCyan
import com.example.eduapp.ui.theme.EduCyanBg
import com.example.eduapp.ui.theme.EduPurple
import com.example.eduapp.ui.theme.EduPurpleBg
import com.example.eduapp.ui.theme.EduTextMuted
import com.example.eduapp.ui.theme.EduTextPrimary
import com.example.eduapp.ui.theme.EduTextSecondary
import com.example.eduapp.ui.theme.EduappTheme

data class Course(
    val id: Int,
    val title: String,
    val category: String,
    val lessons: String,
    val description: String,
    val icon: ImageVector,
    val iconBg: Color,
    val iconTint: Color
)

@Composable
fun CoursesScreen(
    modifier: Modifier = Modifier,
    onCourseClick: (Course) -> Unit = {}
) {
    var searchText by rememberSaveable { mutableStateOf("") }
    var selectedCategory by rememberSaveable { mutableStateOf("Todos") }

    val categories = listOf("Todos", "Código", "Diseño")

    val allCourses = listOf(
        Course(
            id = 1,
            title = "HTML y CSS",
            category = "Código",
            lessons = "12 lecciones",
            description = "Aprende la estructura básica y estilos web desde cero.",
            icon = Icons.Outlined.Code,
            iconBg = EduPurpleBg,
            iconTint = EduPurple
        ),
        Course(
            id = 2,
            title = "Diseño UI",
            category = "Diseño",
            lessons = "8 lecciones",
            description = "Fundamentos de interfaz, tipografía, color y espaciado.",
            icon = Icons.Outlined.DesktopMac,
            iconBg = EduCyanBg,
            iconTint = EduCyan
        ),
        Course(
            id = 3,
            title = "JavaScript Básico",
            category = "Código",
            lessons = "15 lecciones",
            description = "Variables, funciones y lógica práctica para páginas interactivas.",
            icon = Icons.Outlined.Terminal,
            iconBg = EduBlueLight,
            iconTint = EduBlue
        )
    )

    val filteredCourses = allCourses.filter { course ->
        val matchesCategory = selectedCategory == "Todos" || course.category == selectedCategory
        val matchesSearch = searchText.isBlank() ||
                course.title.contains(searchText, ignoreCase = true) ||
                course.description.contains(searchText, ignoreCase = true)
        matchesCategory && matchesSearch
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(EduBg)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(14.dp))

            // Buscador controlado por estado
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = {
                    Text(
                        text = "Buscar curso...",
                        color = EduTextMuted,
                        fontSize = 14.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar",
                        tint = EduTextMuted,
                        modifier = Modifier.size(20.dp)
                    )
                },
                trailingIcon = {
                    AnimatedVisibility(visible = searchText.isNotEmpty()) {
                        IconButton(onClick = { searchText = "" }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Limpiar búsqueda",
                                tint = EduTextMuted,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = EduBlue,
                    unfocusedBorderColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Chips de filtrado interactivos
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                categories.forEach { category ->
                    val isSelected = category == selectedCategory
                    FilterChipCustom(
                        text = category,
                        isSelected = isSelected,
                        onClick = { selectedCategory = category }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Encabezado de sección
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Cursos Disponibles",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = EduTextPrimary
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(EduChipBg)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "${filteredCourses.size} módulos",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = EduChipText
                    )
                }
            }

            Spacer(modifier = Modifier.height(2.dp))
        }

        // Listado de tarjetas de cursos
        items(filteredCourses, key = { it.id }) { course ->
            CourseCard(
                course = course,
                onViewCourse = { onCourseClick(course) }
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun FilterChipCustom(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(if (isSelected) EduBlue else EduChipBg)
            .clickable { onClick() }
            .padding(horizontal = 18.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 13.sp,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
            color = if (isSelected) Color.White else EduChipText
        )
    }
}

@Composable
fun CourseCard(
    course: Course,
    onViewCourse: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            // Fila superior: Ícono y lecciones
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(course.iconBg),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = course.icon,
                        contentDescription = null,
                        tint = course.iconTint,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Text(
                    text = course.lessons,
                    fontSize = 12.sp,
                    color = EduTextMuted,
                    fontWeight = FontWeight.Normal
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = course.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = EduTextPrimary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = course.description,
                fontSize = 13.sp,
                color = EduTextSecondary,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onViewCourse,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                colors = ButtonDefaults.buttonColors(containerColor = EduBlue),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Ver curso",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoursesScreenPreview() {
    EduappTheme {
        CoursesScreen()
    }
}
