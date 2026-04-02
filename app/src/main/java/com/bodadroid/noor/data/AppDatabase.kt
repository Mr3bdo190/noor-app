package com.bodadroid.noor.data

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "quests")
data class Quest(
    @PrimaryKey val id: Int,
    val title: String,
    val points: Int,
    val isCompleted: Boolean = false
)

@Dao
interface QuestDao {
    @Query("SELECT * FROM quests")
    fun getAllQuests(): Flow<List<Quest>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quests: List<Quest>)

    @Update
    suspend fun updateQuest(quest: Quest)
}

@Database(entities = [Quest::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questDao(): QuestDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "noor_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
