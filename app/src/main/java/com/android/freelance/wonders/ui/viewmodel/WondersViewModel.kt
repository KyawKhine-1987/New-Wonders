package com.android.freelance.wonders.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.android.freelance.wonders.data.repository.WondersRepository

class WondersViewModel(
    repository: WondersRepository
) : ViewModel() {

    val callWebService = repository.service

    val getAllWonders = repository.getAllWonders()

    //val saveWonders = repository.saveWonders()
    //val deleteAllWonders = repository.deleteAllWonders()
}

