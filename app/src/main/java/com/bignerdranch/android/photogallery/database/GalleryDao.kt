package com.bignerdranch.android.photogallery.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bignerdranch.android.photogallery.Item

@Dao

interface GalleryDao {
    @Query("SELECT * FROM gallery")
    fun getPhotos(): LiveData<List<Item>>
    @Insert
    fun addPhoto(item:Item)
    @Query("SELECT * FROM gallery WHERE url=(:url)")
    fun getPhoto(url: String): Item?
    @Query("DELETE FROM gallery")
    fun deletePhotos()
}