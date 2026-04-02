package com.bodadroid.noor.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bodadroid.noor.service.FocusModeService
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                NoorAppScreen()
            }
        }
    }
}

@Composable
fun NoorAppScreen() {
    val context = LocalContext.current
    val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val bgColors = when (hour) {
        in 5..11 -> listOf(Color(0xFFFFD54F), Color(0xFFFFB300)) // الصباح
        in 12..17 -> listOf(Color(0xFF29B6F6), Color(0xFF0288D1)) // الظهر
        else -> listOf(Color(0xFF1A237E), Color(0xFF0D47A1)) // الليل
    }

    Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(bgColors))) {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("نور", fontSize = 40.sp, color = Color.White)
            Spacer(modifier = Modifier.height(30.dp))
            
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.2f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("تدبر اليوم من AI:", color = Color.White)
                    Text("سَيَجْعَلُ اللَّهُ بَعْدَ عُسْرٍ يُسْرًا", color = Color.White, fontSize = 18.sp)
                }
            }
            
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                context.startService(Intent(context, FocusModeService::class.java))
            }) {
                Text("تفعيل وضع الخشوع")
            }
        }
    }
}
