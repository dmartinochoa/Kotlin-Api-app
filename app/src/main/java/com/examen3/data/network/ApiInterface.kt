package com.examen3.data.network

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("https://api.adorable.io/avatars/face/{eyes}/{nose}/{mouth}/EDEDFF/200")
    suspend fun getAvatar(
        @Path("eyes") eyes: String,
        @Path("nose") nose: String,
        @Path("mouth") mouth: String
    ): Int

}