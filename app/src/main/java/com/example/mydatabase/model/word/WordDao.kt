package com.example.mydatabase.model.word

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mydatabase.model.word.Word

@Dao
interface WordDao {
    @Query("SELECT *from word_table ORDER BY word ASC")
    fun getAlphabetized(): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(word: Word)

    @Update
    suspend fun update(word: Word)
}