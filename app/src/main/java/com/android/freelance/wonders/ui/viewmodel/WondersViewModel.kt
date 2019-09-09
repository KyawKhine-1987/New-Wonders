package com.android.freelance.wonders.ui.viewmodel

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.android.freelance.wonders.data.repository.WondersRepository
import com.android.freelance.wonders.ui.util.lazyDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class WondersViewModel(
    private val repository: WondersRepository
) : ViewModel() {

    val callWebService = repository.service

    val getAllWonders by lazyDeferred {
        repository.getAllWonders()
    }

    //val saveWonders = repository.saveWonders()
    //val deleteAllWonders = repository.deleteAllWonders()

    //Binding Wonders Detail Components
    /*val i : Intent ?= null
    val detailImage = i?.getStringExtra("image")
    val detailLocation = i?.getStringExtra("title")
    val detailDescription = i?.getStringExtra("desp")*/
}

