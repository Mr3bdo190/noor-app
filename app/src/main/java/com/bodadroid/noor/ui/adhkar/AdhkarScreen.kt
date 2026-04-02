package com.bodadroid.noor.ui.adhkar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AdhkarScreen() {
    val adhkarList = AdhkarRepository.morningAdhkar

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF0F172A))) {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            item {
                Text("أذكار الصباح", color = Color(0xFF38BDF8), fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(20.dp))
            }
            items(adhkarList) { zikr ->
                ZikrCard(zikr)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun ZikrCard(zikr: Zikr) {
    var remainingCount by remember { mutableIntStateOf(zikr.count) }
    val isDone = remainingCount == 0

    val bgColor = if (isDone) Color(0xFF10B981).copy(alpha = 0.2f) else Color(0xFF1E293B)
    val borderColor = if (isDone) Color(0xFF10B981) else Color(0xFF334155)

    Card(
        modifier = Modifier.fillMaxWidth().clickable(enabled = !isDone) {
            if (remainingCount > 0) remainingCount--
        },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        border = BorderStroke(1.dp, borderColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = zikr.text,
                color = Color.White,
                fontSize = 18.sp,
                lineHeight = 28.sp,
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isDone) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.CheckCircle, contentDescription = "Done", tint = Color(0xFF10B981))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("اكتمل", color = Color(0xFF10B981), fontWeight = FontWeight.Bold)
                    }
                } else {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF38BDF8).copy(0.2f)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("المتبقي: $remainingCount", color = Color(0xFF38BDF8), modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp), fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
