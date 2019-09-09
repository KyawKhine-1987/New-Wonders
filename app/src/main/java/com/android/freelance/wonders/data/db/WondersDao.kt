package com.android.freelance.wonders.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.freelance.wonders.data.db.entity.Wonders

@Dao
interface WondersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(wonders: List<Wonders>)//suspend

    @Query("select * from wonders nolock order by id asc;")
    fun fetchAllWonders(): LiveData<List<Wonders>>

    @Delete
    suspend fun deleteAllWonders(wonders: List<Wonders>)
    /*@Query("delete from wonders;")
    suspend fun deleteAllWonders()*/
}