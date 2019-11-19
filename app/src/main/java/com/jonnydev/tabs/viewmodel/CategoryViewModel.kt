package com.jonnydev.tabs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonnydev.tabs.model.Post
import com.jonnydev.tabs.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(private val categoryId: Long?) : ViewModel() {
    val posts = MutableLiveData<List<Post>>().apply {
        viewModelScope.launch(Dispatchers.IO) {
            val postsFromApi = when (categoryId) {
                null, -1L -> PostRepository.getAllPosts()
                else -> PostRepository.getPostsByCategory(categoryId)
            }
            postValue(postsFromApi)
        }
    }
}
