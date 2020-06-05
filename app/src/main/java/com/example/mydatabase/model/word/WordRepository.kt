package com.example.mydatabase.model.word

import androidx.lifecycle.LiveData
import com.example.mydatabase.model.word.Word
import com.example.mydatabase.model.word.WordDao

class WordRepository(private val wordDao: WordDao) {
    val allWords: LiveData<List<Word>> = wordDao.getAlphabetized()

    suspend fun insert(word: Word){
        wordDao.insert(word)
    }

    suspend fun delete(word: Word){
        wordDao.delete(word)
    }

    suspend fun update(word: Word){
        wordDao.update(word)
    }
}