package com.android.freelance.wonders.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.freelance.wonders.data.db.entity.Wonders

@Dao
interface WondersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(wonders: List<Wonders>)//suspend

    @Query("select * from Wonders nolock order by id asc;")
    fun fetchAllWonders(): LiveData<List<Wonders>>

    @Query("delete from Wonders;")
    fun deleteAllWonders()
}