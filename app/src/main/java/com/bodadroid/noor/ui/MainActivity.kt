package com.bodadroid.noor.ui

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.bodadroid.noor.data.AppDatabase
import com.bodadroid.noor.ui.adhkar.AdhkarScreen
import com.bodadroid.noor.ui.prayer.PrayerScreen
import com.bodadroid.noor.ui.quran.QuranIndexScreen
import com.bodadroid.noor.ui.qibla.QiblaScreen
import com.bodadroid.noor.service.FocusModeService

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = AppDatabase.getDatabase(this)
        val factory = MainViewModelFactory(database.questDao())

        setContent {
            MaterialTheme {
                NoorAppMainNavigation(factory)
            }
        }
    }
}

@Composable
fun NoorAppMainNavigation(factory: MainViewModelFactory) {
    val navController = rememberNavController()
    val viewModel: MainViewModel = viewModel(factory = factory)

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF0F172A)) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val navItems = listOf(
                    Triple("home", Icons.Default.Home, "نور"),
                    Triple("quran", Icons.Default.MenuBook, "المصحف"),
                    Triple("prayer", Icons.Default.AccessTime, "الصلاة"),
                    Triple("adhkar", Icons.Default.ListAlt, "الأذكار"),
                    Triple("qibla", Icons.Default.Explore, "القبلة")
                )

                navItems.forEach { (route, icon, label) ->
                    NavigationBarItem(
                        icon = { Icon(icon, contentDescription = label) },
                        label = { Text(label, fontSize = 10.sp) },
                        selected = currentRoute == route,
                        onClick = { 
                            navController.navigate(route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF38BDF8),
                            unselectedIconColor = Color.Gray,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController, 
            startDestination = "home", 
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen(viewModel) }
            composable("quran") { QuranIndexScreen(onSurahClick = { /* سيتم إضافة شاشة القراءة لاحقاً */ }) }
            composable("prayer") { PrayerScreen() }
            composable("adhkar") { AdhkarScreen() }
            composable("qibla") { QiblaScreen() }
        }
    }
}
