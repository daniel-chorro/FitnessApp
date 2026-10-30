package org.insbaixcamp.fitnessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.MonitorWeight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.insbaixcamp.fitnessapp.ui.screen.EditProfileScreen
import org.insbaixcamp.fitnessapp.ui.screen.ProfileScreen
import org.insbaixcamp.fitnessapp.ui.screen.TrainingScreen
import org.insbaixcamp.fitnessapp.ui.screen.WeightTrackingScreen
import org.insbaixcamp.fitnessapp.ui.theme.FitnessAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitnessAppTheme {
                FitnessApp()
            }
        }
    }
}

sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    object Training : BottomNavItem("training", "Entrenament", Icons.Default.FitnessCenter)
    object Tracking : BottomNavItem("tracking", "Seguiment", Icons.Default.MonitorWeight)
    object Profile : BottomNavItem("profile", "Perfil", Icons.Default.Person)
}

@Composable
fun FitnessApp() {
    val navController = rememberNavController()

    var nom: String by remember { mutableStateOf("Laia Soler") }
    var email: String by remember { mutableStateOf("laia.soler@example.com") }
    var dataNaixement: Long by remember { mutableStateOf(946684800000) }
    var genere: String by remember { mutableStateOf("Dona") }
    var editant: Boolean by remember { mutableStateOf(false) }
    var pes: Double by remember { mutableStateOf(60.0) }
    var pesObjectiu: Double by remember { mutableStateOf(55.0) }
    var alçada: Int by remember { mutableStateOf(170) }
    var objectiu: String by remember { mutableStateOf("Guanyar massa muscular") }

    val items = listOf(
        BottomNavItem.Training,
        BottomNavItem.Tracking,
        BottomNavItem.Profile
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = currentRoute == item.route,
                        onClick = {
                            if (currentRoute != item.route) {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Profile.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Training.route) {
                TrainingScreen()
            }
            composable(BottomNavItem.Tracking.route) {
                WeightTrackingScreen(pes = pes, pesObjectiu = pesObjectiu, alçada = alçada)
            }
            composable(BottomNavItem.Profile.route) {
                if (editant) {
                    EditProfileScreen(
                        nom, email, dataNaixement, genere, pes, pesObjectiu, alçada, objectiu,
                        onSave = { nomN, emailN, dataNaixementN, genereN, pesN, pesObjectiuN, alçadaN ->
                            nom = nomN
                            email = emailN
                            dataNaixement = dataNaixementN
                            genere = genereN
                            pes = pesN
                            pesObjectiu = pesObjectiuN
                            alçada = alçadaN
                            editant = false
                        }
                    )
                } else {
                    ProfileScreen(nom, email, dataNaixement, genere, pes, alçada, objectiu, onEditar = { editant = true })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FitnessAppPreview() {
    FitnessAppTheme {
        FitnessApp()
    }
}
