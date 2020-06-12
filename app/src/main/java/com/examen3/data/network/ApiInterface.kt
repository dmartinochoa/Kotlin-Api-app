package com.examen3.data.network

import com.examen3.data.model.Face
import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {
    @GET("avatars/list")
    suspend fun getAvatarList(
    ): AvatarList

    data class AvatarList(
        @SerializedName("face")
        val avatarList: Face
    )

    object AvatarApiFactory {
        fun get(): ApiInterface {
            val retrofit = Retrofit.Builder().baseUrl("https://api.adorable.io/")
                .addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit.create(ApiInterface::class.java)
        }
    }

}