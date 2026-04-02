package com.bodadroid.noor.ui.qibla

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class QiblaViewModel : ViewModel() {
    // زاوية دوران البوصلة (0 = شمال) - سيتم ربطها بالحساسات لاحقاً
    private val _azimuth = MutableStateFlow(0f)
    val azimuth: StateFlow<Float> = _azimuth

    // زاوية القبلة بالنسبة للشمال (القاهرة مثلاً)
    val qiblaAngle = 135f // زاوية افتراضية

    // دالة لتحديث الزاوية من الحساسات (ستستخدم في خطوة قادمة)
    fun updateAzimuth(newAzimuth: Float) {
        _azimuth.value = newAzimuth
    }
}
