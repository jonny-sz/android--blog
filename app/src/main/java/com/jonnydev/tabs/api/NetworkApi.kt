package com.jonnydev.tabs.api

import com.jonnydev.tabs.model.Category
import com.jonnydev.tabs.model.Post
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_API_URL = "http://jonny-dev.com/api/"

interface NetworkApi {
    @GET("post")
    suspend fun getAllPosts() : Response<List<Post>>

    @GET("category")
    suspend fun getAllCategories() : Response<List<Category>>

    @GET("category/{id}/posts")
    suspend fun getPostsByCategory(@Path("id") categoryId: Long) : Response<List<Post>>

    companion object {
        fun build(): NetworkApi = Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NetworkApi::class.java)
    }
}
