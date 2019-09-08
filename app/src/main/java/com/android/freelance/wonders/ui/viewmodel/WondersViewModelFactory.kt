package com.android.freelance.wonders.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.freelance.wonders.data.repository.WondersRepository

@Suppress("UNCHECKED_CAST")
class WondersViewModelFactory(private val repository: WondersRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WondersViewModel(repository) as T
    }
}