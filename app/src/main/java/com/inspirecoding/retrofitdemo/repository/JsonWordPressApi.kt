package com.inspirecoding.retrofitdemo.repository

import com.inspirecoding.retrofitdemo.model.User
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface JsonWordPressApi
{
    @FormUrlEncoded
    @POST("wp/v2/users")
    suspend fun registerUser(
        @Field("email") email:String,
        @Field("username") username:String,
        @Field("password") password:String
    ): User
}