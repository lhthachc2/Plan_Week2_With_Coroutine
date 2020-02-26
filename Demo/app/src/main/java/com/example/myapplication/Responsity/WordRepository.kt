package com.example.myapplication.Responsity

import androidx.lifecycle.LiveData
import com.example.myapplication.Database.Model
import com.example.myapplication.Database.WordDao

class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<Model>> = wordDao.getAlphabetizedWords()

    suspend fun insert(model: Model) {
        wordDao.insert(model)
    }

    fun isValidAccount(username: String, password: String): Boolean{
        val userAcuont  = wordDao.getUserPass(username,password)
        if(userAcuont == null )
            return false
        else
            return userAcuont.Password == password
    }
}