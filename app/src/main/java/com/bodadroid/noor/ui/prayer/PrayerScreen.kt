package com.bodadroid.noor.ui.prayer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PrayerScreen() {
    val prayers = PrayerRepository.todayPrayers

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF0F172A))) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            
            // الهيدر
            Text("مواقيت الصلاة", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Text("القاهرة، مصر", color = Color.Gray, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(24.dp))

            // كارت الصلاة القادمة
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF38BDF8))
            ) {
                Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("الصلاة القادمة", color = Color(0xFF0F172A), fontSize = 16.sp)
                    Text("الظـهر", color = Color.White, fontSize = 36.sp, fontWeight = FontWeight.Bold)
                    Text("متبقي: 01:25:10", color = Color.White, fontSize = 18.sp)
                }
            }
            
            Spacer(modifier = Modifier.height(30.dp))

            // قائمة الصلوات
            LazyColumn {
                items(prayers) { prayer ->
                    PrayerCard(prayer)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun PrayerCard(prayer: PrayerTime) {
    val bgColor = if (prayer.isNext) Color(0xFF1E293B) else Color.Transparent
    val borderColor = if (prayer.isNext) Color(0xFF38BDF8) else Color(0xFF334155)
    val textColor = if (prayer.isNext) Color(0xFF38BDF8) else Color.White

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        border = BorderStroke(1.dp, borderColor)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.Notifications, contentDescription = "Athan", tint = if (prayer.isNext) Color(0xFF38BDF8) else Color.Gray)
                Spacer(modifier = Modifier.width(16.dp))
                Text(prayer.name, color = textColor, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Row(verticalAlignment = Alignment.Bottom) {
                Text(prayer.time, color = textColor, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(4.dp))
                Text(prayer.amPm, color = Color.Gray, fontSize = 14.sp, modifier = Modifier.padding(bottom = 3.dp))
            }
        }
    }
}
