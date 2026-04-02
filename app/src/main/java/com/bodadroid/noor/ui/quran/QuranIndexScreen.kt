package com.bodadroid.noor.ui.quran

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QuranIndexScreen(onSurahClick: (Surah) -> Unit) {
    val surahs = QuranRepository.surahsList

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF0F172A))) {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            item {
                Text("المصحف الشريف", color = Color(0xFF38BDF8), fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(20.dp))
            }
            items(surahs) { surah ->
                SurahCard(surah, onSurahClick)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun SurahCard(surah: Surah, onClick: (Surah) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick(surah) },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        border = BorderStroke(1.dp, Color(0xFF334155))
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            
            // رقم السورة بتصميم دائري
            Card(shape = RoundedCornerShape(50.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFF38BDF8).copy(0.1f))) {
                Text(text = surah.id.toString(), color = Color(0xFF38BDF8), modifier = Modifier.padding(12.dp), fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.width(16.dp))
            
            // اسم السورة وبياناتها
            Column(modifier = Modifier.weight(1f)) {
                Text(text = surah.name, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Right)
                Text(text = "آياتها ${surah.verses} - ${surah.type}", color = Color.Gray, fontSize = 14.sp)
            }
        }
    }
}
