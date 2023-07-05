package com.everybodv.testauth

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [User::class, Transaction::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        @OptIn(DelicateCoroutinesApi::class)
        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                val database = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "user_database"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Memasukkan data dummy user saat database pertama kali dibuat
                            GlobalScope.launch(Dispatchers.IO) {
                                val userDao = getInstance(context).userDao()
                                userDao.addUser(User(username = "user1", password = "user123", role = "user"))
                                userDao.addUser(User(username = "admin1", password = "admin123", role = "admin"))
                                userDao.addUser(User(username = "manager1", password = "manager123", role = "manager"))
                                // Tambahkan data dummy user lainnya jika diperlukan
                            }
                        }
                    })
                    .build()
                instance = database
                database
            }
        }
    }
}