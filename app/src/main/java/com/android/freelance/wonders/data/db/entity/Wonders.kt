package com.android.freelance.wonders.data.db.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

private const val wonder_Id = 0

@Entity
data class Wonders(

    var location: String? = null,

    var description: String? = null,

    @ColumnInfo(name = "image_url")
    var image: String? = null,

    @ColumnInfo(name = "latitude")
    var lat: String? = null,

    @SerializedName("long")
    @ColumnInfo(name = "longitude")
    var longitude: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = wonder_Id
}