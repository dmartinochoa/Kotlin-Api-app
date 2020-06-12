package com.examen3.data.repository.local

import com.examen3.data.model.User


class RoomLocalRepository(private val userDatabase: UserDatabase) : LocalRepository {
    override suspend fun insertUser(username: String, name: String, url: String) {
        userDatabase.userDao().insertUser(UserEntity(username = username, name = name, url = url))
    }

    override suspend fun getUser(): User {
        val userEntities = userDatabase.userDao().getUser()
        return User(userEntities.id, userEntities.username, userEntities.name, userEntities.url)
    }
}
