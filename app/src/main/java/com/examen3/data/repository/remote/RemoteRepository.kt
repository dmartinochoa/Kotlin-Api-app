package com.examen3.data.repository.remote

import com.examen3.data.model.Face

interface RemoteRepository{
    suspend fun getAvatarList(): Face
}