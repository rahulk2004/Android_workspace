package com.example.module7coroutines

import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): List<Post>
}