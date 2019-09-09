package com.android.freelance.wonders.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.lifecycle.LiveData
import com.android.freelance.wonders.data.db.WondersDatabase
import com.android.freelance.wonders.data.db.entity.Wonders
import com.android.freelance.wonders.data.network.ApiWonders
import com.android.freelance.wonders.ui.util.Coroutines
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WondersRepository(
    api: ApiWonders,
    private val db: WondersDatabase,
    context: Context
) {

    val service = api.getWondersList()
        .subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({

            /*Thread(Runnable {*/
            val wondersListFromNetwork = it.wonders

            val wondersListEntity = ArrayList<Wonders>()
            for (wondersFromNetwork in wondersListFromNetwork) {

                val wonders = Wonders()
                wonders.location = wondersFromNetwork.location
                wonders.image = wondersFromNetwork.image
                wonders.description = wondersFromNetwork.description
                wonders.lat = wondersFromNetwork.lat
                wonders.longitude = wondersFromNetwork.longitude
                wondersListEntity.add(wonders)
            }

            Coroutines.io {
                db.wondersDao().deleteAllWonders(wondersListEntity)
                db.wondersDao().insert(wondersListEntity)
            }
            /*this.deleteAllWonders()
            this.saveWonders(wondersListEntity)*/

            /*}).start()*/
        }, {
            Toast.makeText(
                context,
                "No Internet Connection!\nThis is offline data which is taken to show you from the local storage.\nAlso " + it.message,
                Toast.LENGTH_LONG
            ).show()
        })

    suspend fun getAllWonders(): LiveData<List<Wonders>> {
        return withContext(Dispatchers.IO) {
            db.wondersDao().fetchAllWonders()
        }
    }

    /*private suspend fun saveWonders(wonders: List<Wonders>) =
        db.wondersDao().insert(wonders)

    private suspend fun deleteAllWonders() = db.wondersDao().deleteAllWonders()*/
}