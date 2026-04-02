package com.bodadroid.noor.ui.quran

data class Surah(val id: Int, val name: String, val verses: Int, val type: String)

object QuranRepository {
    // فهرس مبدئي لعرض التصميم
    val surahsList = listOf(
        Surah(1, "الفاتحة", 7, "مكية"),
        Surah(2, "البقرة", 286, "مدنية"),
        Surah(3, "آل عمران", 200, "مدنية"),
        Surah(4, "النساء", 176, "مدنية"),
        Surah(5, "المائدة", 120, "مدنية")
    )
}
