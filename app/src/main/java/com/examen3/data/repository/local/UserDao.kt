package com.examen3.data.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM USERS LIMIT 1 ")
    suspend fun getUser(): UserEntity
}