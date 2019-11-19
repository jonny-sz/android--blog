package com.jonnydev.tabs.repository

import com.jonnydev.tabs.api.NetworkApi
import com.jonnydev.tabs.model.Entity
import com.jonnydev.tabs.model.Post
import retrofit2.Response

object PostRepository {
    private val networkApi = NetworkApi.build()

    suspend fun getAllPosts(): List<Post>? {
        val response = networkApi.getAllPosts()

        return when {
            response.isSuccessful -> response.body()
            else -> null
        }
    }

    suspend fun getPostsByCategory(id: Long): List<Post>? {
        val response = networkApi.getPostsByCategory(id)

        return when {
            response.isSuccessful -> response.body()
            else -> null
        }
    }
}
