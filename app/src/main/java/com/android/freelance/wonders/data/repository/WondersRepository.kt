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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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
                this.deleteAllWonders()
                this.saveWonders(wondersListEntity)
            /*}).start()*/
        }, {
            Toast.makeText(
                context,
                "No Internet Connection!\nThis is offline data which is taken to show you from the local storage.\nAlso " + it.message,
                Toast.LENGTH_LONG
            ).show()
        })

    private fun saveWonders(wonders: List<Wonders>) = db.wondersDao().insert(wonders)//suspend

    fun getAllWonders(): LiveData<List<Wonders>> = db.wondersDao().fetchAllWonders()

    private fun deleteAllWonders() = db.wondersDao().deleteAllWonders()
}