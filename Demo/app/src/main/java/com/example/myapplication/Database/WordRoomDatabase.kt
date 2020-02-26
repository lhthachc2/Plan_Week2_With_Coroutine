package com.example.myapplication.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Model::class], version = 3, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): WordRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.wordDao())
                    }
                }
            }
        }
        suspend fun populateDatabase(wordDao: WordDao) {
            var word = Model("Hello12","2","3","4","5")
            wordDao.insert(word)
            word = Model("World!12","6","7","8","9")
            wordDao.insert(word)
            word = Model("World!123","6","7","8","9")
            wordDao.insert(word)
            word = Model("World!124","6","7","8","9")
            wordDao.insert(word)
            word = Model("World!125","6","7","8","9")
            wordDao.insert(word)
            word = Model("World!126","6","7","8","9")
            wordDao.insert(word)
            word = Model("World!127","6","7","8","9")
            wordDao.insert(word)
            word = Model("World!128","6","7","8","9")
            wordDao.insert(word)
            word = Model("World!129","6","7","8","9")
            wordDao.insert(word)
        }
    }

}