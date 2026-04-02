package com.bodadroid.noor.ui.prayer

data class PrayerTime(val id: Int, val name: String, val time: String, val amPm: String, val isNext: Boolean = false)

object PrayerRepository {
    // بيانات مبدئية لعرض التصميم (سيتم ربطها بالـ GPS لاحقاً)
    val todayPrayers = listOf(
        PrayerTime(1, "الفجر", "04:30", "ص"),
        PrayerTime(2, "الشروق", "05:50", "ص"),
        PrayerTime(3, "الظهر", "12:00", "م", isNext = true), // الصلاة القادمة
        PrayerTime(4, "العصر", "03:30", "م"),
        PrayerTime(5, "المغرب", "06:15", "م"),
        PrayerTime(6, "العشاء", "07:45", "م")
    )
}
