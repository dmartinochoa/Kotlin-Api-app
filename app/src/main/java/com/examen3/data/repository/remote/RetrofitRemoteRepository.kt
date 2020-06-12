package com.examen3.data.repository.remote

import android.accounts.NetworkErrorException
import com.examen3.data.model.Face
import com.examen3.data.network.ApiInterface

class RetrofitRemoteRepository : RemoteRepository{
    private val avatarApi = ApiInterface.AvatarApiFactory.get()
    override suspend fun getAvatarList(): Face {
        try {
            val response = avatarApi.getAvatarList()
            if (response == null) {
                throw NetworkErrorException("Error fetching Avatar list")
            }
            return response.avatarList
        } catch (e: Exception) {
            throw NetworkErrorException("Error fetching weather")
        }
    }

}