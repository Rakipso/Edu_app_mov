package com.example.eduapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.eduapp.ui.theme.EduappTheme
import com.example.eduapp.ui.theme.components.EduNavigationBar
import com.example.eduapp.ui.theme.components.EduTopBar
import com.example.eduapp.ui.theme.screens.CoursesScreen
import com.example.eduapp.ui.theme.screens.HomeScreen
import com.example.eduapp.ui.theme.screens.ProfileScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EduappTheme {
                EduApp()
            }
        }
    }
}

@Composable
fun EduApp(
    initialTab: Tab = Tab.INICIO,
    modifier: Modifier = Modifier
) {
    var currentTab by rememberSaveable { mutableStateOf(initialTab) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            EduTopBar(subtitle = currentTab.label)
        },
        bottomBar = {
            EduNavigationBar(
                selectedTab = currentTab,
                onTabSelected = { newTab -> currentTab = newTab }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentTab) {
                Tab.INICIO -> HomeScreen(
                    onContinueClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Continuando curso: Fundamentos de Diseño")
                        }
                    },
                    onTasksClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Abriendo Mis Tareas...")
                        }
                    },
                    onClassesClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Abriendo Mis Clases...")
                        }
                    }
                )

                Tab.CURSOS -> CoursesScreen(
                    onCourseClick = { course ->
                        scope.launch {
                            snackbarHostState.showSnackbar("Iniciando: ${course.title}")
                        }
                    }
                )

                Tab.PERFIL -> ProfileScreen(
                    onEditProfileClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Abriendo edición de perfil")
                        }
                    },
                    onNotificationsClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Configuración de notificaciones")
                        }
                    },
                    onHelpClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Centro de ayuda y soporte")
                        }
                    },
                    onLogoutClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Cerrando sesión...")
                        }
                    }
                )
            }
        }
    }
}

@Preview(name = "1. Vista Inicio", showBackground = true, widthDp = 360, heightDp = 740)
@Composable
fun EduAppInicioPreview() {
    EduappTheme {
        EduApp(initialTab = Tab.INICIO)
    }
}

@Preview(name = "2. Vista Cursos", showBackground = true, widthDp = 360, heightDp = 740)
@Composable
fun EduAppCursosPreview() {
    EduappTheme {
        EduApp(initialTab = Tab.CURSOS)
    }
}

@Preview(name = "3. Vista Perfil", showBackground = true, widthDp = 360, heightDp = 740)
@Composable
fun EduAppPerfilPreview() {
    EduappTheme {
        EduApp(initialTab = Tab.PERFIL)
    }
}