package com.jonnydev.tabs.model

import java.io.Serializable

interface Entity

data class Post(
        val id: Long,
        val title: String,
        val description: String,
        val text: String,
        val created: String,
        val category: Category,
        val user: User
) : Serializable, Entity


data class User(
        val id: Long,
        val username: String
) : Serializable, Entity


data class Category(
        val id: Long,
        val title: String
) : Serializable, Entity
