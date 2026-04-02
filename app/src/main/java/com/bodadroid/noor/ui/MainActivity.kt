package com.bodadroid.noor.ui

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.bodadroid.noor.data.AppDatabase
import com.bodadroid.noor.data.Quest
import com.bodadroid.noor.service.FocusModeService

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = AppDatabase.getDatabase(this)
        val factory = MainViewModelFactory(database.questDao())

        setContent {
            MaterialTheme {
                NoorAppNavigation(factory)
            }
        }
    }
}

@Composable
fun NoorAppNavigation(factory: MainViewModelFactory) {
    val navController = rememberNavController()
    val viewModel: MainViewModel = viewModel(factory = factory)

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF0F172A)) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val items = listOf("home" to Icons.Default.Home, "tasbeeh" to Icons.Default.Refresh, "quests" to Icons.Default.Star)
                items.forEach { (route, icon) ->
                    NavigationBarItem(
                        icon = { Icon(icon, contentDescription = null) },
                        selected = currentRoute == route,
                        onClick = { navController.navigate(route) },
                        colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFF38BDF8), indicatorColor = Color.Transparent)
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = "home", Modifier.padding(innerPadding)) {
            composable("home") { HomeScreen(viewModel) }
            composable("tasbeeh") { TasbeehScreen() }
            composable("quests") { QuestsScreen(viewModel) }
        }
    }
}

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val context = LocalContext.current
    val totalPoints by viewModel.totalPoints.collectAsState()

    Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color(0xFF0F172A), Color(0xFF1E293B))))) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text("أهلاً بك في نـور", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Text("رصيدك الإيماني: $totalPoints نقطة", color = Color(0xFF38BDF8))
            
            Spacer(modifier = Modifier.height(30.dp))
            
            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White.copy(0.05f))) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("تدبر اليوم", color = Color.White, fontWeight = FontWeight.Bold)
                    Text("﴿إِنَّ مَعَ الْعُسْرِ يُسْرًا﴾", color = Color.LightGray, fontSize = 18.sp, modifier = Modifier.padding(top = 10.dp))
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
            
            Button(
                onClick = {
                    val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    if (!nm.isNotificationPolicyAccessGranted) {
                        context.startActivity(Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS))
                    } else {
                        context.startService(Intent(context, FocusModeService::class.java))
                    }
                },
                modifier = Modifier.fillMaxWidth().height(60.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF38BDF8))
            ) {
                Text("تفعيل وضع الخشوع", color = Color(0xFF0F172A), fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun TasbeehScreen() {
    var count by remember { mutableIntStateOf(0) }
    
    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF0F172A)), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("المسبحة الإلكترونية", color = Color.White, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(50.dp))
            
            Box(
                modifier = Modifier.size(250.dp).background(Color(0xFF38BDF8).copy(0.1f), CircleShape)
                    .border(2.dp, Color(0xFF38BDF8), CircleShape).clickable { count++ },
                contentAlignment = Alignment.Center
            ) {
                Text("$count", fontSize = 80.sp, color = Color.White, fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(40.dp))
            IconButton(onClick = { count = 0 }) {
                Icon(Icons.Default.Refresh, contentDescription = "Reset", tint = Color.Gray, modifier = Modifier.size(40.dp))
            }
        }
    }
}

@Composable
fun QuestsScreen(viewModel: MainViewModel) {
    val quests by viewModel.quests.collectAsState()
    
    LazyColumn(modifier = Modifier.fillMaxSize().background(Color(0xFF0F172A)).padding(20.dp)) {
        item { Text("تحدياتك اليومية", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold); Spacer(modifier = Modifier.height(20.dp)) }
        items(quests) { quest ->
            QuestItemCard(quest = quest, onClick = { viewModel.toggleQuest(quest) })
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun QuestItemCard(quest: Quest, onClick: () -> Unit) {
    val color = if (quest.isCompleted) Color(0xFF10B981) else Color.White
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = if (quest.isCompleted) color.copy(0.1f) else Color(0xFF1E293B)),
        border = BorderStroke(1.dp, color.copy(0.3f))
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(if (quest.isCompleted) Icons.Default.CheckCircle else Icons.Default.Star, contentDescription = null, tint = color)
            Spacer(modifier = Modifier.width(15.dp))
            Text(quest.title, color = color, fontSize = 16.sp)
        }
    }
}
