package com.examen3.data.repository.local

import com.examen3.data.model.User

interface LocalRepository {
    suspend fun insertUser(username:String, name:String, url:String)
    suspend fun  getUser():User
}