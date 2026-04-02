package com.bodadroid.noor.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
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
                val viewModel: MainViewModel = viewModel(factory = factory)
                NoorMainScreen(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoorMainScreen(viewModel: MainViewModel) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("الرئيسية", "المهام", "الإعدادات")
    val icons = listOf(Icons.Filled.Home, Icons.Filled.Star, Icons.Filled.Settings)
    
    val quests by viewModel.quests.collectAsState()
    val totalPoints by viewModel.totalPoints.collectAsState()
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF1E293B), contentColor = Color.White) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(icons[index], contentDescription = item, tint = if (selectedItem == index) Color(0xFF38BDF8) else Color.Gray) },
                        label = { Text(item, color = if (selectedItem == index) Color(0xFF38BDF8) else Color.Gray) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent)
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues).background(Brush.verticalGradient(listOf(Color(0xFF0F172A), Color(0xFF1E293B))))) {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                
                // الهيدر والنتيجة
                item {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Column {
                            Text("نـور", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color(0xFF38BDF8))
                            Text("تطبيقك الإسلامي", fontSize = 14.sp, color = Color.LightGray)
                        }
                        Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF10B981).copy(alpha = 0.2f)), shape = RoundedCornerShape(12.dp)) {
                            Text("النقاط: $totalPoints", color = Color(0xFF10B981), fontWeight = FontWeight.Bold, modifier = Modifier.padding(12.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }

                // كارت التدبر
                item {
                    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f))) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Filled.Star, contentDescription = "AI", tint = Color(0xFFFBBF24))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("تدبر اليوم بالذكاء الاصطناعي", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Text("﴿سَيَجْعَلُ اللَّهُ بَعْدَ عُسْرٍ يُسْرًا﴾\nلا تقلق، مهما طال الصبر، الفرج قريب.", color = Color.LightGray, fontSize = 18.sp, lineHeight = 28.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }

                // زر وضع الخشوع
                item {
                    Button(
                        onClick = { context.startService(Intent(context, FocusModeService::class.java)) },
                        modifier = Modifier.fillMaxWidth().height(55.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF38BDF8)),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("تفعيل وضع الخشوع", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }

                // قائمة المهام
                item {
                    Text("تحديات اليوم (اضغط للإكمال)", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(12.dp))
                }

                items(quests) { quest ->
                    QuestItemCard(quest = quest, onClick = { viewModel.toggleQuest(quest) })
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun QuestItemCard(quest: Quest, onClick: () -> Unit) {
    val bgColor = if (quest.isCompleted) Color(0xFF10B981).copy(alpha = 0.2f) else Color(0xFF334155)
    val textColor = if (quest.isCompleted) Color(0xFF10B981) else Color.White

    Card(modifier = Modifier.fillMaxWidth().clickable { onClick() }, shape = RoundedCornerShape(12.dp), colors = CardDefaults.cardColors(containerColor = bgColor)) {
        Row(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = quest.title, color = textColor, fontSize = 16.sp, fontWeight = if(quest.isCompleted) FontWeight.Bold else FontWeight.Normal)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "+${quest.points}", color = textColor, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                if (quest.isCompleted) {
                    Icon(Icons.Filled.CheckCircle, contentDescription = "Done", tint = Color(0xFF10B981))
                }
            }
        }
    }
}
