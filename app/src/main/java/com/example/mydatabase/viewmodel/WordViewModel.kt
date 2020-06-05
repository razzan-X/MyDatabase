package com.example.mydatabase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mydatabase.database.MyDatabase
import com.example.mydatabase.model.word.Word
import com.example.mydatabase.model.word.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: WordRepository
    val allWords: LiveData<List<Word>>

    init {
        val wordsDao = MyDatabase.getDatabase(
            application,
            viewModelScope
        ).wordDao()
        repository = WordRepository(wordsDao)
        allWords = repository.allWords
    }

    fun insert(word: Word)=viewModelScope.launch(Dispatchers.IO){
        repository.insert(word)
    }

    fun delete(word: Word)= viewModelScope.launch(Dispatchers.IO){
        repository.delete(word)
    }

    fun update(word: Word)=viewModelScope.launch(Dispatchers.IO){
        repository.update(word)
    }
}