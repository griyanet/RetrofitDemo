package com.inspirecoding.retrofitdemo.repository

import com.inspirecoding.retrofitdemo.model.User
import retrofit2.http.*

interface JsonWordPressApi
{
    @GET("wp/v2/users")
    suspend fun getUser(
        @Query("search") email: String
    ): List<User>

    @GET("wp/v2/users")
    suspend fun getAllUser(): List<User>

    @FormUrlEncoded
    @POST("wp/v2/users")
    suspend fun registerUser(
        @Field("email") email:String,
        @Field("username") username:String,
        @Field("password") password:String
    ): User
}