package com.jonnydev.tabs.repository

import com.jonnydev.tabs.api.NetworkApi
import com.jonnydev.tabs.model.Category
import java.lang.Exception

object CategoryRepository {
    private val networkApi = NetworkApi.build()

    suspend fun getAllCategories(): List<Category>? {
        val response = networkApi.getAllCategories()

        return when {
            response.isSuccessful -> response.body()
            else -> null
        }
    }
}
