package com.jonnydev.tabs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonnydev.tabs.model.Category
import com.jonnydev.tabs.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val categories = MutableLiveData<List<Category>>().apply {
        viewModelScope.launch(Dispatchers.IO) {
            val categoriesFromApi = CategoryRepository.getAllCategories()
            
            postValue(categoriesFromApi)
        }
    }
}
