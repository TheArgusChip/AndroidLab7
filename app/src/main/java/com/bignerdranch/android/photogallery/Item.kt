package com.bignerdranch.android.photogallery

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "gallery")
data class Item(
    var title: String = "",
    @PrimaryKey var id: String = "",
    @SerializedName("url_s") var url: String = ""
)