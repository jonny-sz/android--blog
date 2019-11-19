package com.jonnydev.tabs.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class CategoryViewModelFactory(private val categoryId: Long?) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoryViewModel(categoryId) as T
    }
}
