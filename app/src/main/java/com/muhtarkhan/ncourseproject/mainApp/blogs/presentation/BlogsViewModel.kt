package com.muhtarkhan.ncourseproject.mainApp.blogs.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.muhtarkhan.ncourseproject.appEntryActivity.data.account.AccountProvider
import com.muhtarkhan.ncourseproject.mainApp.blogs.domain.usecase.GetBlogsUseCase
import com.muhtarkhan.ncourseproject.mainApp.home.presentation.HomeEvent
import com.muhtarkhan.ncourseproject.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class BlogsViewModel(private val getBlogsUseCase: GetBlogsUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(BlogsUIState(isLoading = true))
    val uiState: StateFlow<BlogsUIState> = _uiState.asStateFlow()

    init {
        fetchBlogs()
    }


    fun dispatch(event: BlogsEvent, navController: NavController, accountProvider: AccountProvider){
        when(event){
            is BlogsEvent.onLogOut -> {
                accountProvider.clearToken()
                navController.navigate(R.id.action_blogsFragment_to_loginFragment)
            }
        }
    }

    fun fetchBlogs() {
        viewModelScope.launch {
            getBlogsUseCase()
                .onEach { blogs ->
                    _uiState.value = BlogsUIState(isLoading = false, blogsData = blogs)
                }
                .catch { error ->
                    _uiState.value = BlogsUIState(isLoading = false, error = error.message)
                }
                .launchIn(viewModelScope)
        }
    }

}