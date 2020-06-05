package com.example.mydatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mydatabase.model.product.Product
import com.example.mydatabase.model.product.ProductDao
import com.example.mydatabase.model.word.Word
import com.example.mydatabase.model.word.WordDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities= [Word::class, Product::class], version=1, exportSchema=false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun productDao(): ProductDao
    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback(){
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var wordDao = database.wordDao()
                    wordDao.deleteAll()
                   /* var productDao = database.productDao()
                    productDao.deleteAll()*/
                    var word = Word(  "hello")
                    wordDao.insert(word)
                }
            }
        }
    }
    companion object{
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): MyDatabase{
         return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "word_database"
                ).addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE=instance
                 instance
            }
        }
    }
}