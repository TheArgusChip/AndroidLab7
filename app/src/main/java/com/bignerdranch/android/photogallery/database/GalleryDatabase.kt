package com.bignerdranch.android.photogallery.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bignerdranch.android.photogallery.Item

@Database(entities = [Item::class], version = 1)
abstract class GalleryDatabase: RoomDatabase() {
    abstract fun galleryDao(): GalleryDao
}