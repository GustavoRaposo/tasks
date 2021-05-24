package com.gustavoraposo.roomexample.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gustavoraposo.roomexample.data.database.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(user: UserEntity)

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUser(id: Long): UserEntity

    @Query("SELECT id FROM user WHERE username = :username and password = :password ")
    fun login(username: String, password: String): LiveData<Long>
}