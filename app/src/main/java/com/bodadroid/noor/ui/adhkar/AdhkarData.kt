package com.bodadroid.noor.ui.adhkar

data class Zikr(val id: Int, val text: String, val count: Int, val type: String)

object AdhkarRepository {
    val morningAdhkar = listOf(
        Zikr(1, "آية الكرسي: ﴿اللَّهُ لَا إِلَٰهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ...﴾", 1, "صباح"),
        Zikr(2, "بِسـمِ اللهِ الذي لا يَضُـرُّ مَعَ اسمِـهِ شَيءٌ في الأرْضِ وَلا في السّمـاءِ وَهـوَ السّمـيعُ العَلـيم.", 3, "صباح"),
        Zikr(3, "رَضيـتُ بِاللهِ رَبَّـاً وَبِالإسْلامِ ديـناً وَبِمُحَـمَّدٍ صلى الله عليه وسلم نَبِيّـاً.", 3, "صباح")
    )
}
