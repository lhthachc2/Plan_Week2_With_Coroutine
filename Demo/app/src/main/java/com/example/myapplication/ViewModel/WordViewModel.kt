package com.example.myapplication.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Database.Model
import com.example.myapplication.Database.WordRoomDatabase
import com.example.myapplication.Responsity.WordRepository
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WordRepository
    val allWords: LiveData<List<Model>>

    init {
        val wordsDao = WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repository = WordRepository(wordsDao)
        allWords = repository.allWords
    }
    fun insert(model: Model) = viewModelScope.launch {
        repository.insert(model)
    }
    fun checkValidLogin(username: String, password: String): Boolean {
        return repository.isValidAccount(username,password)
    }
}