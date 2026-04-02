package com.bodadroid.noor.ui.qibla

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QiblaScreen(viewModel: QiblaViewModel = QiblaViewModel()) {
    val context = LocalContext.current
    val azimuth by viewModel.azimuth.collectAsState()
    
    // أنيميشن ناعم لدوران الإبرة
    val animatedAzimuth by animateFloatAsState(targetValue = -azimuth)

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF0F172A))) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            
            // الهيدر
            Text("بوصلة القبلة", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Text("تأكد من تفعيل الـ GPS ومعايرة الحساسات", color = Color.Gray, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(40.dp))

            // البوصلة (رسم بالـ Canvas)
            Box(contentAlignment = Alignment.Center) {
                
                // رسم خلفية البوصلة (الشمال والجنوب)
                Canvas(modifier = Modifier.size(300.dp)) {
                    val radius = size.minDimension / 2
                    val center = Offset(size.width / 2, size.height / 2)
                    
                    // دائرة أساسية
                    drawCircle(color = Color(0xFF334155), radius = radius, center = center)
                    drawCircle(color = Color(0xFF38BDF8), radius = radius, center = center, style = androidx.compose.ui.graphics.drawscope.Stroke(4.dp))
                }

                // رسم إبرة القبلة الدوارة
                Canvas(modifier = Modifier.size(280.dp)) {
                    val center = Offset(size.width / 2, size.height / 2)
                    val radius = size.minDimension / 2
                    
                    // دوران الإبرة بناءً على حساسات الموبايل وزاوية القبلة
                    rotate(animatedAzimuth + viewModel.qiblaAngle, pivot = center) {
                        
                        // الإبرة
                        drawLine(
                            color = Color(0xFFFF4444), // لون القبلة
                            start = center,
                            end = Offset(center.x, center.y - radius + 20.dp.toPx()),
                            strokeWidth = 10.dp.toPx()
                        )
                        drawLine(
                            color = Color.White, // لون الشمال
                            start = center,
                            end = Offset(center.x, center.y + radius - 20.dp.toPx()),
                            strokeWidth = 10.dp.toPx()
                        )
                        drawCircle(color = Color.White, radius = 15.dp.toPx(), center = center)
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(40.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.LocationOn, contentDescription = "Loc", tint = Color(0xFF38BDF8))
                Spacer(modifier = Modifier.width(8.dp))
                Text("القاهرة - مصر", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}
