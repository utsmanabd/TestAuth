package com.everybodv.testauth
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user: User)

    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getUser(username: String): User?

//    @Query("SELECT id, time, numItem, total FROM transaction WHERE idUser = :userId")
//    suspend fun getTransaction(userId: Int): Transaction?
//
//    @Insert
//    suspend fun addTransaction(transaction: Transaction)

//    @Query("SELECT SUM(total) FROM transaction WHERE idUser = :userId")
//    suspend fun getTotalByUser(userId: Int): Transaction?
//
//    @Query("SELECT username FROM users where id = :userId")
//    suspend fun getUserById(userId: Int): User?
}
