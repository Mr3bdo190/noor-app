package com.bodadroid.noor.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bodadroid.noor.data.Quest
import com.bodadroid.noor.data.QuestDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(private val dao: QuestDao) : ViewModel() {
    private val _quests = MutableStateFlow<List<Quest>>(emptyList())
    val quests: StateFlow<List<Quest>> = _quests

    private val _totalPoints = MutableStateFlow(0)
    val totalPoints: StateFlow<Int> = _totalPoints

    init {
        viewModelScope.launch {
            dao.getAllQuests().collectLatest { dbQuests ->
                if (dbQuests.isEmpty()) {
                    val initial = listOf(
                        Quest(1, "صلاة الضحى (ركعتين)", 50),
                        Quest(2, "أذكار الصباح", 30),
                        Quest(3, "قراءة صفحة من القرآن", 40),
                        Quest(4, "ركعتين قيام ليل", 60)
                    )
                    dao.insertAll(initial)
                } else {
                    _quests.value = dbQuests
                    _totalPoints.value = dbQuests.filter { it.isCompleted }.sumOf { it.points }
                }
            }
        }
    }

    fun toggleQuest(quest: Quest) {
        viewModelScope.launch {
            dao.updateQuest(quest.copy(isCompleted = !quest.isCompleted))
        }
    }
}

class MainViewModelFactory(private val dao: QuestDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
