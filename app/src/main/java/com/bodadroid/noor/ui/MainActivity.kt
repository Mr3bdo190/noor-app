package com.bodadroid.noor.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.bodadroid.noor.service.FocusModeService

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                NoorMainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoorMainScreen() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("الرئيسية", "المهام", "الإعدادات")
    val icons = listOf(Icons.Filled.Home, Icons.Filled.Star, Icons.Filled.Settings)

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF1E293B),
                contentColor = Color.White
            ) {
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
        // خلفية متدرجة فخمة (Night Mode Style)
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Brush.verticalGradient(listOf(Color(0xFF0F172A), Color(0xFF1E293B))))) {
            
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // قسم الترحيب
                item {
                    Text(text = "نـور", fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Color(0xFF38BDF8))
                    Text(text = "تطبيقك الإسلامي الذكي", fontSize = 16.sp, color = Color.LightGray)
                    Spacer(modifier = Modifier.height(30.dp))
                }

                // كارت الذكاء الاصطناعي (التدبر)
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f))
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Filled.Star, contentDescription = "AI", tint = Color(0xFFFBBF24))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("تدبر اليوم بالذكاء الاصطناعي", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                "﴿سَيَجْعَلُ اللَّهُ بَعْدَ عُسْرٍ يُسْرًا﴾\nرسالة اليوم: لا تقلق، مهما طال الصبر، الفرج قريب.",
                                color = Color.LightGray, fontSize = 18.sp, lineHeight = 28.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }

                // قسم التحديات والسنن
                item {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text("تحديات اليوم (السنن المهجورة)", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        QuestItem(title = "صلاة الضحى (ركعتين)", points = "+50 نقطة")
                        Spacer(modifier = Modifier.height(8.dp))
                        QuestItem(title = "أذكار الصباح", points = "+30 نقطة")
                        Spacer(modifier = Modifier.height(8.dp))
                        QuestItem(title = "قراءة صفحة من القرآن", points = "+40 نقطة")
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }

                // زر وضع الخشوع (Focus Mode)
                item {
                    val context = LocalContext.current
                    Button(
                        onClick = { context.startService(Intent(context, FocusModeService::class.java)) },
                        modifier = Modifier.fillMaxWidth().height(55.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF38BDF8)),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("تفعيل وضع الخشوع (منع الإشعارات)", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
fun QuestItem(title: String, points: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF334155))
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, color = Color.White, fontSize = 16.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = points, color = Color(0xFF10B981), fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Filled.CheckCircle, contentDescription = "Done", tint = Color.Gray)
            }
        }
    }
}
